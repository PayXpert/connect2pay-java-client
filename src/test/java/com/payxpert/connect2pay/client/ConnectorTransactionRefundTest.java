package com.payxpert.connect2pay.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.requests.TransactionRefundRequest;
import com.payxpert.connect2pay.client.response.TransactionRefundResponse;

public class ConnectorTransactionRefundTest extends ConnectorTransactionTest {
  @Before
  public void init() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
  }

  /**
   * Transaction refund request with an invalid ID
   * 
   */
  @Test
  public void transactionRefundTestInvalidId() {
    TransactionRefundResponse refundResponse = null;
    TransactionRefundRequest refundRequest = new TransactionRefundRequest();
    refundRequest.setTransactionId("666999666");
    refundRequest.setAmount(100);

    try {
      refundResponse = connector.refundTransaction(refundRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(refundResponse);
    Assert.assertNotNull(refundResponse.getCode());
    Assert.assertEquals("312", refundResponse.getCode());
  }

  /**
   * Transaction refund request with a missing amount
   * 
   */
  @Test
  public void transactionRefundTestMissingAmount() {
    TransactionRefundResponse refundResponse = null;
    TransactionRefundRequest refundRequest = new TransactionRefundRequest();
    refundRequest.setTransactionId("666999666");

    try {
      refundResponse = connector.refundTransaction(refundRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(refundResponse);
    Assert.assertNotNull(refundResponse.getCode());
    Assert.assertEquals("401", refundResponse.getCode());
  }
}
