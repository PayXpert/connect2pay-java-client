package com.payxpert.connect2pay.client.requests;

import org.junit.Assert;
import org.junit.Test;

import com.payxpert.connect2pay.constants.WeChatPaymentMode;
import com.payxpert.connect2pay.exception.BadRequestException;

public class WeChatDirectProcessRequestTest {

  @Test
  public void testNativeRequestWellformed() throws Exception {
    WeChatDirectProcessRequest request = new WeChatDirectProcessRequest();
    request.setCustomerToken("12345678");
    request.setMode(WeChatPaymentMode.NATIVE);

    // No exception must be thrown
    request.validate();
  }

  @Test
  public void testQuickPayRequestWellformed() throws Exception {
    WeChatDirectProcessRequest request = new WeChatDirectProcessRequest();
    request.setCustomerToken("12345678");
    request.setMode(WeChatPaymentMode.QUICKPAY);
    request.setQuickPayCode("123456789012345678");

    // No exception must be thrown
    request.validate();
  }

  @Test
  public void testNativeRequestLongField() throws Exception {
    WeChatDirectProcessRequest request = new WeChatDirectProcessRequest();
    request.setCustomerToken("12345678");
    request.setMode(WeChatPaymentMode.NATIVE);
    request.setNotificationLang("123456789012");
    request.setNotificationTimeZone("1234567890123456789012345678901234567890123456789012345678901234567890");

    // No exception must be thrown
    request.validate();
    Assert.assertTrue(request.getNotificationLang().length() <= 10);
    Assert.assertTrue(request.getNotificationTimeZone().length() <= 64);
  }

  @Test(expected = BadRequestException.class)
  public void testMissingMode() throws Exception {
    WeChatDirectProcessRequest request = new WeChatDirectProcessRequest();

    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testMissingQuickPayAuthCode() throws Exception {
    WeChatDirectProcessRequest request = new WeChatDirectProcessRequest();
    request.setCustomerToken("12345678");
    request.setMode(WeChatPaymentMode.QUICKPAY);

    request.validate();
  }
}
