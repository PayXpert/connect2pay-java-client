package com.payxpert.connect2pay.client.requests;

import org.junit.Test;

import com.payxpert.connect2pay.exception.BadRequestException;

public class PaymentStatusRequestTest {
  @Test
  public void testPaymentStatusRequestWellformed() throws Exception {
    PaymentStatusRequest request = getDefaultRequest();

    // No exception must be thrown
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testPaymentStatusRequestMissingTransactionId() throws Exception {
    PaymentStatusRequest request = getDefaultRequest();
    request.setMerchantToken(null);
    request.validate();
  }

  public static PaymentStatusRequest getDefaultRequest() {
    PaymentStatusRequest request = new PaymentStatusRequest();

    request.setMerchantToken("a2xta2VyKm1sw6lrbWFza2ZtKmHDqW1MS8OpbWZ6YWxmZGs=");

    return request;
  }
}
