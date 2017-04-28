package com.payxpert.connect2pay.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.requests.TransactionRefundRequest;
import com.payxpert.connect2pay.client.response.TransactionRefundResponse;
import com.payxpert.connect2pay.constants.ResultCode;

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
    Assert.assertEquals(ResultCode.TRANSACTION_NOT_FOUND, refundResponse.getCode());
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
    Assert.assertEquals(ResultCode.DATA_MISSING_PARAMETER, refundResponse.getCode());
  }
}
