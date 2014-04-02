package com.payxpert.connect2pay.client;

import org.junit.Assert;
import org.junit.Test;

import com.payxpert.connect2pay.client.Connect2payClient;
import com.payxpert.connect2pay.client.requests.TransactionRequest;
import com.payxpert.connect2pay.client.requests.TransactionRequestTest;
import com.payxpert.connect2pay.client.response.TransactionResponse;
import com.payxpert.connect2pay.constants.ResultCode;

public class ConnectorPrepareTransactionTest extends ConnectorTransactionTest {
  /**
   * Test with a card number considered as valid by the Payment Gateway Test
   * Provider
   */
  @Test
  public void prepareTransactionTestSuccessfull() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
    TransactionResponse response = null;

    try {
      response = this.connector.prepareTransaction(TransactionRequestTest.getDefaultRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(response);
    Assert.assertEquals(ResultCode.SUCCESS, response.getCode());
    Assert.assertNotNull(response.getMerchantToken());
    Assert.assertNotNull(response.getCustomerToken());
  }

  /**
   * Test with invalid credentials
   */
  @Test
  public void prepareTransactionAuthFailed() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, "BadPassword");
    TransactionResponse response = null;

    try {
      response = this.connector.prepareTransaction(TransactionRequestTest.getDefaultRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(response);
    Assert.assertEquals(ResultCode.AUTH_FAILED, response.getCode());
    Assert.assertNull(response.getMerchantToken());
    Assert.assertNull(response.getCustomerToken());
  }

  /**
   * Test with an invalid request
   * 
   */
  @Test
  public void prepareTransactionTestInvalidRequest() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
    TransactionResponse response = null;

    TransactionRequest request = TransactionRequestTest.getDefaultRequest();
    request.setCurrency(null);

    try {
      response = this.connector.prepareTransaction(request);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(response);
    Assert.assertEquals(ResultCode.DATA_MISSING_PARAMETER, response.getCode());
    Assert.assertNull(response.getMerchantToken());
    Assert.assertNull(response.getCustomerToken());
  }
}
