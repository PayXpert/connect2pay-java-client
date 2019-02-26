package com.payxpert.connect2pay.client.requests;

import org.junit.Test;

import com.payxpert.connect2pay.exception.BadRequestException;

public class TransactionRefundConfirmRequestTest {
  @Test
  public void testTransactionRefundConfirmRequestWellformed() throws Exception {
    TransactionRefundConfirmRequest request = getDefaultRequest();

    // No exception must be thrown
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testTransactionRefundConfirmRequestMissingSubscriptionId() throws Exception {
    TransactionRefundConfirmRequest request = getDefaultRequest();
    request.setTransactionId(null);
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testTransactionRefundConfirmRequestMissingReason() throws Exception {
    TransactionRefundConfirmRequest request = getDefaultRequest();
    request.setTransactionId(null);
    request.validate();
  }

  public static TransactionRefundConfirmRequest getDefaultRequest() {
    TransactionRefundConfirmRequest request = new TransactionRefundConfirmRequest();

    request.setTransactionId("123456-01");

    return request;
  }
}
