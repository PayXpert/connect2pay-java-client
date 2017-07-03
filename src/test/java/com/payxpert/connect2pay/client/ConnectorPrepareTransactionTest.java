package com.payxpert.connect2pay.client;

import org.junit.Assert;
import org.junit.Test;

import com.payxpert.connect2pay.client.requests.PaymentRequest;
import com.payxpert.connect2pay.client.requests.TransactionRequestTest;
import com.payxpert.connect2pay.client.response.PaymentResponse;
import com.payxpert.connect2pay.constants.ResultCode;

public class ConnectorPrepareTransactionTest extends ConnectorTransactionTest {
  /**
   * Test with a card number considered as valid by the Payment Gateway Test Provider
   */
  @Test
  public void prepareTransactionTestSuccessfull() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
    PaymentResponse response = null;

    try {
      response = this.connector.preparePayment(TransactionRequestTest.getDefaultRequest());
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(response);
    Assert.assertEquals(ResultCode.SUCCESS, response.getCode());
    Assert.assertNotNull(response.getMerchantToken());
    Assert.assertNotNull(response.getCustomerToken());
    Assert.assertEquals(TEST_URL + "/payment/" + response.getCustomerToken(), response.getCustomerRedirectURL());
  }

  /**
   * Test with invalid credentials
   */
  @Test
  public void prepareTransactionAuthFailed() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, "BadPassword");
    PaymentResponse response = null;

    try {
      response = this.connector.preparePayment(TransactionRequestTest.getDefaultRequest());
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
    PaymentResponse response = null;

    PaymentRequest request = TransactionRequestTest.getDefaultRequest();
    request.setCurrency(null);

    try {
      response = this.connector.preparePayment(request);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(response);
    Assert.assertEquals(ResultCode.DATA_MISSING_PARAMETER, response.getCode());
    Assert.assertNull(response.getMerchantToken());
    Assert.assertNull(response.getCustomerToken());
  }
}
