package com.payxpert.connect2pay.client.requests;

import org.junit.Test;

import com.payxpert.connect2pay.exception.BadRequestException;

public class TransactionInfoRequestTest {
  @Test
  public void testTransactionInfoRequestWellformed() throws Exception {
    TransactionInfoRequest request = getDefaultRequest();

    // No exception must be thrown
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testTransactionInfoRequestMissingTransactionId() throws Exception {
    TransactionInfoRequest request = getDefaultRequest();
    request.setTransactionId(null);
    request.validate();
  }

  public static TransactionInfoRequest getDefaultRequest() {
    TransactionInfoRequest request = new TransactionInfoRequest();

    request.setTransactionId("a2xta2VyKm1sw6lr");

    return request;
  }
}
