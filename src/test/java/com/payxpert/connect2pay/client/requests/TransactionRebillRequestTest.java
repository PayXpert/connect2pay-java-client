package com.payxpert.connect2pay.client.requests;

import org.junit.Test;

import com.payxpert.connect2pay.exception.BadRequestException;

public class TransactionRebillRequestTest {
  @Test
  public void testTransactionRebillRequestWellformed() throws Exception {
    TransactionRebillRequest request = getDefaultRequest();

    // No exception must be thrown
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testTransactionRebillRequestMissingTransactionId() throws Exception {
    TransactionRebillRequest request = getDefaultRequest();
    request.setTransactionId(null);
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testTransactionRebillRequestMissingAmount() throws Exception {
    TransactionRebillRequest request = getDefaultRequest();
    request.setAmount(null);
    request.validate();
  }

  public static TransactionRebillRequest getDefaultRequest() {
    TransactionRebillRequest request = new TransactionRebillRequest();

    request.setTransactionId("123456-01").setAmount(1599);

    return request;
  }
}
