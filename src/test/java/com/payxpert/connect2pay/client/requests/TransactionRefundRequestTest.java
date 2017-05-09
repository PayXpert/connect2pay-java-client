package com.payxpert.connect2pay.client.requests;

import org.junit.Test;

import com.payxpert.connect2pay.exception.BadRequestException;

public class TransactionRefundRequestTest {
  @Test
  public void testTransactionRefundRequestWellformed() throws Exception {
    TransactionRefundRequest request = getDefaultRequest();

    // No exception must be thrown
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testTransactionRefundRequestMissingSubscriptionId() throws Exception {
    TransactionRefundRequest request = getDefaultRequest();
    request.setTransactionId(null);
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testTransactionRefundRequestMissingReason() throws Exception {
    TransactionRefundRequest request = getDefaultRequest();
    request.setAmount(null);
    request.validate();
  }

  public static TransactionRefundRequest getDefaultRequest() {
    TransactionRefundRequest request = new TransactionRefundRequest();

    request.setTransactionId("123456-01").setAmount(1599);

    return request;
  }
}
