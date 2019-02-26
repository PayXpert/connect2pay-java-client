package com.payxpert.connect2pay.client.response;

import org.junit.Assert;
import org.junit.Test;

import com.payxpert.connect2pay.constants.TransactionOperation;

public class TransactionCancelResponseTest {
  @Test
  public void testTransactionCancelResponseDeserialization() throws Exception {
    String json = "{\"transactionID\":\"1kva092fgq-01-r05\",\"code\":\"012\",\"message\":\"Invalid Transaction\",\"operation\":\"cancel\"}";
    TransactionCancelResponse response = new TransactionCancelResponse().fromJson(json);

    Assert.assertNotNull(response);
    Assert.assertEquals("012", response.getCode());
    Assert.assertEquals("1kva092fgq-01-r05", response.getTransactionId());
    Assert.assertEquals("Invalid Transaction", response.getMessage());
    Assert.assertEquals(TransactionOperation.CANCEL, response.getOperation());
  }
}
