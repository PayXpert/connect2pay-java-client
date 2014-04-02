package com.payxpert.connect2pay.client;

import org.junit.Assert;
import org.junit.Test;

import com.payxpert.connect2pay.client.Connect2payClient;
import com.payxpert.connect2pay.client.requests.TransactionRequestTest;
import com.payxpert.connect2pay.client.response.TransactionResponse;
import com.payxpert.connect2pay.constants.ResultCode;

public class ConnectorBasicTransactionTest extends ConnectorTransactionTest {
  /**
   * Test with an invalid originator
   */
  @Test
  public void transactionWithBadOriginator() {
    this.connector = new Connect2payClient(TEST_URL, "9999999", DEFAULT_PASSWORD);

    TransactionResponse response = null;

    try {
      response = connector.prepareTransaction(TransactionRequestTest.getDefaultRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(response);
    Assert.assertEquals(ResultCode.AUTH_FAILED, response.getCode());
  }

  /**
   * Test with an invalid password
   */
  @Test
  public void transactionWithBadPassword() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, "AZERTY");

    TransactionResponse response = null;

    try {
      response = connector.prepareTransaction(TransactionRequestTest.getDefaultRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(response);
    Assert.assertEquals(ResultCode.AUTH_FAILED, response.getCode());
  }
}
