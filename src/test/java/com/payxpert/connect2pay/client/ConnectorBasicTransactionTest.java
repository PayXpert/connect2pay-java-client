package com.payxpert.connect2pay.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.payxpert.connect2pay.client.requests.TransactionRequestTest;
import com.payxpert.connect2pay.client.response.PaymentResponse;
import com.payxpert.connect2pay.constants.ResultCode;

public class ConnectorBasicTransactionTest extends ConnectorTransactionTest {
  /**
   * Test with an invalid originator
   */
  @Test
  public void transactionWithBadOriginator() {
    this.connector = new Connect2payClient(TEST_URL, "9999999", DEFAULT_PASSWORD);

    PaymentResponse response = null;

    try {
      response = connector.preparePayment(TransactionRequestTest.getDefaultRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(response);
    assertEquals(ResultCode.AUTH_FAILED, response.getCode());
  }

  /**
   * Test with an invalid password
   */
  @Test
  public void transactionWithBadPassword() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, "AZERTY");

    PaymentResponse response = null;

    try {
      response = connector.preparePayment(TransactionRequestTest.getDefaultRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(response);
    assertEquals(ResultCode.AUTH_FAILED, response.getCode());
  }
}
