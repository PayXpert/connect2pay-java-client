package com.payxpert.connect2pay.client.requests;

import org.junit.Assert;
import org.junit.Test;

import com.payxpert.connect2pay.constants.AlipayIdentityCodeType;
import com.payxpert.connect2pay.constants.AlipayPaymentMode;
import com.payxpert.connect2pay.exception.BadRequestException;

public class AlipayDirectProcessRequestTest {

  @Test
  public void testPosRequestWellformed() throws Exception {
    AlipayDirectProcessRequest request = new AlipayDirectProcessRequest();
    request.setCustomerToken("12345678");
    request.setMode(AlipayPaymentMode.POS);

    // No exception must be thrown
    request.validate();
  }

  @Test
  public void testPosRequestToJson() throws Exception {
    AlipayDirectProcessRequest request = new AlipayDirectProcessRequest();
    request.setCustomerToken("12345678");
    request.setMode(AlipayPaymentMode.POS);

    String json = "{\"apiVersion\":\"002.60\",\"mode\":\"pos\"}";

    Assert.assertEquals(json, request.toJson());
  }

  @Test
  public void testAppRequestWellformed() throws Exception {
    AlipayDirectProcessRequest request = new AlipayDirectProcessRequest();
    request.setCustomerToken("12345678");
    request.setMode(AlipayPaymentMode.APP);
    request.setBuyerIdentityCode("123456789012345678");
    request.setIdentityCodeType(AlipayIdentityCodeType.QRCODE);

    // No exception must be thrown
    request.validate();
  }

  @Test
  public void testAppRequestToJson() throws Exception {
    AlipayDirectProcessRequest request = new AlipayDirectProcessRequest();
    request.setCustomerToken("12345678");
    request.setMode(AlipayPaymentMode.APP);
    request.setBuyerIdentityCode("123456789012345678");
    request.setIdentityCodeType(AlipayIdentityCodeType.QRCODE);

    String json = "{\"apiVersion\":\"002.60\",\"mode\":\"app\",\"identityCodeType\":\"qrcode\",\"buyerIdentityCode\":\"123456789012345678\"}";

    Assert.assertEquals(json, request.toJson());
  }

  @Test
  public void testPosRequestLongField() throws Exception {
    AlipayDirectProcessRequest request = new AlipayDirectProcessRequest();
    request.setCustomerToken("12345678");
    request.setMode(AlipayPaymentMode.POS);
    request.setNotificationLang("123456789012");
    request.setNotificationTimeZone("1234567890123456789012345678901234567890123456789012345678901234567890");

    // No exception must be thrown
    request.validate();
    Assert.assertTrue(request.getNotificationLang().length() <= 10);
    Assert.assertTrue(request.getNotificationTimeZone().length() <= 64);
  }

  @Test(expected = BadRequestException.class)
  public void testMissingMode() throws Exception {
    AlipayDirectProcessRequest request = new AlipayDirectProcessRequest();

    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testMissingAppBuyerIdentityCode() throws Exception {
    AlipayDirectProcessRequest request = new AlipayDirectProcessRequest();
    request.setCustomerToken("12345678");
    request.setMode(AlipayPaymentMode.APP);
    request.setIdentityCodeType(AlipayIdentityCodeType.QRCODE);

    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testMissingAppIdentityCodeType() throws Exception {
    AlipayDirectProcessRequest request = new AlipayDirectProcessRequest();
    request.setCustomerToken("12345678");
    request.setMode(AlipayPaymentMode.APP);
    request.setBuyerIdentityCode("123456789012345678");

    request.validate();
  }
}
