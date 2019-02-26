package com.payxpert.connect2pay.client.requests;

import org.junit.Test;

import com.payxpert.connect2pay.exception.BadRequestException;

public class TransactionCancelRequestTest {
  @Test
  public void testTransactionCancelRequestWellformed() throws Exception {
    TransactionCancelRequest request = getDefaultRequest();

    // No exception must be thrown
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testTransactionCancelRequestMissingTransactionId() throws Exception {
    TransactionCancelRequest request = getDefaultRequest();
    request.setTransactionId(null);
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testTransactionCancelRequestMissingAmount() throws Exception {
    TransactionCancelRequest request = getDefaultRequest();
    request.setAmount(null);
    request.validate();
  }

  public static TransactionCancelRequest getDefaultRequest() {
    TransactionCancelRequest request = new TransactionCancelRequest();

    request.setTransactionId("123456-01").setAmount(1599);

    return request;
  }
}
