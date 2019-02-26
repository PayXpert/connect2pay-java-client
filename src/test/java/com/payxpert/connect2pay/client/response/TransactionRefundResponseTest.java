package com.payxpert.connect2pay.client.response;

import org.junit.Assert;
import org.junit.Test;

import com.payxpert.connect2pay.constants.TransactionOperation;

public class TransactionRefundResponseTest {
  @Test
  public void testTransactionRefundResponseDeserialization() throws Exception {
    String json = "{\"transactionID\":\"1kva092fgq-01-r05\",\"code\":\"012\",\"message\":\"Invalid Transaction\",\"operation\":\"refund_request\"}";
    TransactionRefundResponse response = new TransactionRefundResponse().fromJson(json);

    Assert.assertNotNull(response);
    Assert.assertEquals("012", response.getCode());
    Assert.assertEquals("1kva092fgq-01-r05", response.getTransactionId());
    Assert.assertEquals("Invalid Transaction", response.getMessage());
    Assert.assertEquals(TransactionOperation.REFUND_REQUEST, response.getOperation());
  }
}
