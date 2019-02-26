package com.payxpert.connect2pay.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.security.InvalidKeyException;
import java.util.List;

import javax.crypto.IllegalBlockSizeException;

import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.containers.Shopper;
import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.client.response.PaymentStatusResponse;
import com.payxpert.connect2pay.constants.PaymentMethod;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.utils.CryptoHelper;

public class ConnectorHandleRedirectTest extends ConnectorTransactionTest {
  @Before
  public void init() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
  }

  /**
   * Successful redirect data handling
   */
  @Test
  public void handleRedirectTestSuccessfull() {
    String merchantToken = "W2n1yR8UXNJBrJxbsqOy3g";
    String encryptedData = this.getEncryptedData(merchantToken);
    PaymentStatusResponse response = null;
    try {
      response = connector.handleRedirectStatus(encryptedData, merchantToken);
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(response);
    assertNotNull(response.getMerchantToken());
    assertEquals(merchantToken, response.getMerchantToken());
    assertEquals(ResultCode.SUCCESS, response.getCode());

    assertEquals("000", response.getErrorCode());

    List<TransactionAttempt> transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(1, transactionAttempts.size());

    TransactionAttempt transactionAttempt = transactionAttempts.get(0);
    assertEquals(PaymentMethod.CREDIT_CARD, transactionAttempt.getPaymentType());

    Shopper shopper = transactionAttempt.getShopper();
    assertNotNull(shopper);
    assertEquals("Bernard Menez", shopper.getName());
    assertEquals("bernard.menez@gmail.com", shopper.getEmail());
  }

  /**
   * Redirect data handling with incorrect key
   * 
   * @throws Exception
   * 
   */
  @Test(expected = InvalidKeyException.class)
  public void handleRedirectTestInvalidKey() throws Exception {
    String merchantToken = "W2n1yR8UXNJBrJxbsqOy3g";
    String encryptedData = this.getEncryptedData("W2n1yR8UXNJBrJxbsqOy3g");
    // Set an invalid Key
    merchantToken = "W2n1yR8UXNJBrJxbsqOy";

    PaymentStatusResponse response = connector.handleRedirectStatus(encryptedData, merchantToken);

    assertNull(response);
  }

  /**
   * Redirect data handling with incorrect data
   * 
   * @throws Exception
   */
  @Test(expected = IllegalBlockSizeException.class)
  public void handleRedirectTestInvalidData() throws Exception {
    String merchantToken = "W2n1yR8UXNJBrJxbsqOy3g";
    String encryptedData = this.getEncryptedData(merchantToken);
    encryptedData = encryptedData.substring(0, encryptedData.length() - 5);

    PaymentStatusResponse response = connector.handleRedirectStatus(encryptedData, merchantToken);

    assertNull(response);
  }

  public String getEncryptedData(String key) {
    StringBuilder strBuilderStatus = new StringBuilder();
    strBuilderStatus.append('{');
    strBuilderStatus.append("\"transactions\":");
    strBuilderStatus.append('[');
    strBuilderStatus.append('{');
    strBuilderStatus.append("\"paymentType\":\"CreditCard\"");
    strBuilderStatus.append(",\"paymentMeanInfo\":");
    strBuilderStatus.append('{');
    strBuilderStatus.append("\"cardHolderName\":\"Bernard Ménez\"");
    strBuilderStatus.append(",\"is3DSecure\":false}");
    strBuilderStatus.append(",\"shopper\":");
    strBuilderStatus.append('{');
    strBuilderStatus.append("\"name\":\"Bernard Menez\"");
    strBuilderStatus.append(",\"address\":\"Passeig de Gracia, 55\"");
    strBuilderStatus.append(",\"zipcode\":\"08008\"");
    strBuilderStatus.append(",\"city\":\"Barcelona\"");
    strBuilderStatus.append(",\"state\":\"Barcelona\"");
    strBuilderStatus.append(",\"countryCode\":\"ES\"");
    strBuilderStatus.append(",\"phone\":\"+34666666666\"");
    strBuilderStatus.append(",\"email\":\"bernard.menez@gmail.com\"");
    strBuilderStatus.append(",\"birthDate\":\"19700101\"");
    strBuilderStatus.append(",\"idNumber\":\"ID12345\"");
    strBuilderStatus.append('}');
    strBuilderStatus.append(",\"operation\":\"sale\"");
    strBuilderStatus.append('}');
    strBuilderStatus.append(']');
    strBuilderStatus.append(",\"merchantToken\":\"W2n1yR8UXNJBrJxbsqOy3g\"");
    strBuilderStatus.append(",\"paymentType\":\"CreditCard\"");
    strBuilderStatus.append(",\"operation\":\"sale\"");
    strBuilderStatus.append(",\"errorCode\":\"000\"");
    strBuilderStatus.append(",\"status\":\"Not processed\"");
    strBuilderStatus.append(",\"orderId\":\"20170505155125300\"");
    strBuilderStatus.append(",\"currency\":\"EUR\"");
    strBuilderStatus.append(",\"amount\":100");
    strBuilderStatus.append('}');

    try {
      return CryptoHelper.encryptAESURLBase64(strBuilderStatus.toString().getBytes("utf-8"),
          CryptoHelper.fromBase64(key));
    } catch (Exception e1) {
    }

    return "C1w0nrYb6B_2yc3mheyRX9vXvuIr2b3-OZSM8RKzKJqYVZlHOPa4seVUmBOeTJ49xqlPR9DoUskBQsyEzTs74rQYdEDqwYk0n4Vac4wF3t_FxkG-GNd2sBcTmrKaeJXLnRuKP1KHHEIi-fZTzwe5pNnOon4bgxJJF_L-PuSHq4xs2P6OJjCUPW_pg3s9bRxdJKHAYaV11EOfwmX_O0dSIyPMCBhOGilmeC7obVY1ePYUW_9w1SgGMZJBslm6LYT3g_adsb2FxfUErJM9wH1eHjAQCG-OJbKx7Ii_4xzMDYM7a3V8DwZZPXBXW9EQI9kQLY2M9kD65uKYvFZd-bmTSAM03n5_bcyUtSznlIG3jE6ZMQmBwDcHAXKzpjtVTqAMa_VfRe-YhGVNdGRebxLjc_Ko96xHxJfMgai6k5P5mCNqIYEDvcCCqHlUU7LCY7yjJSBdAlG-AUPjy62Jmy-G_AHcrFycVgcl4IMvxhaS3Oll2h1ismzU_7qy7XQzcWyDKY8SLuYEoJdQ1u0mTO-u9WlmlQI13Gtwt9V0zDJsWwV8uIaUStX0CFMNDTxAFc8bQIsTST1s1GTb5vJ6vhA4Yawc_kkvfTn43GShjBoy6ahiUID02K6v7qZQrkluOcPT5REyVBFre6BQdDYFzCbAblMQQnD_CBquK7yFHDq8Cu4uJ5LTA_I2ZFtJpatNm1_eqen7QhFLpGZXrrU-rIK9grb44-RximkMjgi5rbropALtPXr5nA3RWZTaHr5h_Lw675pXHJfaf9piOpazuJ45m5RyHk7tgW0u2cf6NYuESt-ozXDyf59XLneSERlt24btK5UE3bB7AfKUQRp10";

  }
}
