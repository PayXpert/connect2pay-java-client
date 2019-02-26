package com.payxpert.connect2pay.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.requests.TransactionRebillRequest;
import com.payxpert.connect2pay.client.response.TransactionRebillResponse;

public class ConnectorTransactionRebillTest extends ConnectorTransactionTest {
  @Before
  public void init() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
  }

  /**
   * Transaction rebill request with an invalid ID
   */
  @Test
  public void transactionRebillTestInvalidId() {
    TransactionRebillResponse rebillResponse = null;
    TransactionRebillRequest rebillRequest = new TransactionRebillRequest();
    rebillRequest.setTransactionId("666999666");
    rebillRequest.setAmount(100);

    try {
      rebillResponse = connector.rebillTransaction(rebillRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(rebillResponse);
    Assert.assertNotNull(rebillResponse.getCode());
    Assert.assertEquals("312", rebillResponse.getCode());
  }

  /**
   * Transaction rebill request with a missing amount
   * 
   */
  @Test
  public void transactionRebillTestMissingAmount() {
    TransactionRebillResponse rebillResponse = null;
    TransactionRebillRequest rebillRequest = new TransactionRebillRequest();
    rebillRequest.setTransactionId("666999666");

    try {
      rebillResponse = connector.rebillTransaction(rebillRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(rebillResponse);
    Assert.assertNotNull(rebillResponse.getCode());
    Assert.assertEquals("401", rebillResponse.getCode());
  }
}
