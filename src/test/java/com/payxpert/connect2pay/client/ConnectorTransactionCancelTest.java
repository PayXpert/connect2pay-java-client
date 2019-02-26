package com.payxpert.connect2pay.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.requests.TransactionCancelRequest;
import com.payxpert.connect2pay.client.response.TransactionCancelResponse;

public class ConnectorTransactionCancelTest extends ConnectorTransactionTest {
  @Before
  public void init() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
  }

  /**
   * Transaction cancel request with an invalid ID
   */
  @Test
  public void transactionCancelTestInvalidId() {
    TransactionCancelResponse cancelResponse = null;
    TransactionCancelRequest cancelRequest = new TransactionCancelRequest();
    cancelRequest.setTransactionId("666999666");
    cancelRequest.setAmount(100);

    try {
      cancelResponse = connector.cancelTransaction(cancelRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(cancelResponse);
    Assert.assertNotNull(cancelResponse.getCode());
    Assert.assertEquals("312", cancelResponse.getCode());
  }

  /**
   * Transaction cancel request with a missing amount
   */
  @Test
  public void transactionCancelTestMissingAmount() {
    TransactionCancelResponse cancelResponse = null;
    TransactionCancelRequest cancelRequest = new TransactionCancelRequest();
    cancelRequest.setTransactionId("666999666");

    try {
      cancelResponse = connector.cancelTransaction(cancelRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(cancelResponse);
    Assert.assertNotNull(cancelResponse.getCode());
    Assert.assertEquals("401", cancelResponse.getCode());
  }
}
