package com.payxpert.connect2pay.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper containing various crypto methods.
 * 
 * @author jsh
 * 
 */
public class CryptoHelper {
  private static final Logger logger = LoggerFactory.getLogger(CryptoHelper.class);

  public static String sha512(String text) {
    String result = null;

    MessageDigest md = null;

    try {
      md = MessageDigest.getInstance("SHA-512");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    if (md != null) {
      try {
        result = Hex.encodeHexString(md.digest(text.getBytes("UTF-8")));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  public static String generateRandomBase64(int size) {
    return Base64.encodeBase64URLSafeString(CryptoHelper.generateRandom(size));
  }

  /**
   * Provide random data.
   * 
   * @param size
   *          The size of the desired random data in bits.
   * @return An array of bytes filled with the random data
   */
  public static byte[] generateRandom(int size) {
    size = size / 8;

    byte[] results = new byte[size];

    SecureRandom secRand = new SecureRandom();

    secRand.nextBytes(results);

    return results;
  }

  public static byte[] generateAESKey(int size) {
    KeyGenerator keygen;
    SecretKey key = null;

    try {
      keygen = KeyGenerator.getInstance("AES");
      keygen.init(size);
      key = keygen.generateKey();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    if (key != null) {
      return key.getEncoded();
    } else {
      return null;
    }
  }

  public static String toURLBase64(byte[] data) {
    return Base64.encodeBase64URLSafeString(data);
  }

  public static String toBase64(byte[] data) {
    return Base64.encodeBase64String(data);
  }

  public static byte[] fromBase64(String text) {
    return Base64.decodeBase64(text);
  }

  public static String generateAESKeyURLBase64(int size) {
    return Base64.encodeBase64URLSafeString(CryptoHelper.generateAESKey(size));
  }

  public static String encryptAESURLBase64(String text, byte[] encryptionKey, byte[] encryptionIV) throws Exception {
    return Base64.encodeBase64URLSafeString(encryptAES(text, encryptionKey, encryptionIV));
  }

  public static String encryptAESURLBase64(byte[] data, byte[] key) throws Exception {
    return Base64.encodeBase64URLSafeString(encryptAES(data, key));
  }

  public static byte[] encryptAES(String text, byte[] encryptionKey, byte[] encryptionIV) throws Exception {
    if (text != null) {
      byte[] data = null;
      try {
        data = text.getBytes("UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        return null;
      }

      return encryptAES(data, encryptionKey, encryptionIV);
    }
    return null;
  }

  public static byte[] encryptAES(byte[] data, byte[] encryptionKey) throws Exception {
    if (data == null || encryptionKey == null) {
      return null;
    }
    return encryptAES(data, encryptionKey, null);
  }

  public static byte[] encryptAES(byte[] data, byte[] encryptionKey, byte[] encryptionIV) throws Exception {
    return doCryptAES(Cipher.ENCRYPT_MODE, data, encryptionKey, encryptionIV);
  }

  public static String decryptAESBase64ToString(String text, byte[] encryptionKey, byte[] encryptionIV)
      throws Exception {
    byte[] decrypted = decryptAES(Base64.decodeBase64(text), encryptionKey, encryptionIV);
    return (decrypted != null) ? new String(decrypted) : null;
  }

  public static String decryptAESBase64ToString(String text, byte[] encryptionKey) throws Exception {
    byte[] decrypted = decryptAES(Base64.decodeBase64(text), encryptionKey, null);
    return (decrypted != null) ? new String(decrypted) : null;
  }

  public static String decryptAESURLBase64ToString(String text, byte[] encryptionKey) throws Exception {
    byte[] decrypted = decryptAES(Base64.decodeBase64(text), encryptionKey, null);
    return (decrypted != null) ? new String(decrypted) : null;
  }

  public static byte[] decryptAESBase64(String text, byte[] encryptionKey, byte[] encryptionIV) throws Exception {
    return decryptAES(Base64.decodeBase64(text), encryptionKey, encryptionIV);
  }

  public static byte[] decryptAESBase64(String text, byte[] encryptionKey) throws Exception {
    return decryptAES(Base64.decodeBase64(text), encryptionKey, null);
  }

  public static String decryptAESToString(byte[] data, byte[] encryptionKey, byte[] encryptionIV) throws Exception {
    byte[] decrypted = decryptAES(data, encryptionKey, encryptionIV);
    return (decrypted != null) ? new String(decrypted) : null;
  }

  public static String decryptAESToString(byte[] data, byte[] encryptionKey) throws Exception {
    byte[] decrypted = decryptAES(data, encryptionKey, null);
    return (decrypted != null) ? new String(decrypted) : null;
  }

  public static byte[] decryptAES(byte[] data, byte[] encryptionKey) throws Exception {
    return decryptAES(data, encryptionKey, null);
  }

  public static byte[] decryptAES(byte[] data, byte[] encryptionKey, byte[] encryptionIV) throws Exception {
    return doCryptAES(Cipher.DECRYPT_MODE, data, encryptionKey, encryptionIV);
  }

  public static byte[] doCryptAES(int mode, byte[] data, byte[] encryptionKey, byte[] encryptionIV) throws Exception {
    byte[] results = null;

    if (data == null || encryptionKey == null) {
      return null;
    }

    try {
      Cipher aesCipher = null;
      SecretKeySpec skeySpec = new SecretKeySpec(encryptionKey, "AES");

      if (encryptionIV != null) {
        // We have an IV, use CBC mode
        IvParameterSpec ivSpec = new IvParameterSpec(encryptionIV);
        aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        aesCipher.init(mode, skeySpec, ivSpec);
      } else {
        // No IV, ECB mode
        aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        aesCipher.init(mode, skeySpec);
      }

      results = aesCipher.doFinal(data);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("AES operation exception " + e.getClass().getSimpleName() + " - " + e.getMessage());
      throw e;
    }

    return results;
  }
}
