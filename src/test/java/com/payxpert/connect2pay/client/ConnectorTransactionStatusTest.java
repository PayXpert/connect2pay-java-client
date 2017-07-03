package com.payxpert.connect2pay.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.client.requests.PaymentRequest;
import com.payxpert.connect2pay.client.requests.TransactionRequestTest;
import com.payxpert.connect2pay.client.requests.PaymentStatusRequest;
import com.payxpert.connect2pay.client.response.PaymentResponse;
import com.payxpert.connect2pay.client.response.PaymentStatusResponse;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.constants.PaymentStatusValue;

public class ConnectorTransactionStatusTest extends ConnectorTransactionTest {
  @Before
  public void init() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
  }

  /**
   * Successful transaction status request (creation + status)
   */
  @Test
  public void transactionStatusTestSuccessfull() {
    PaymentResponse response = null;
    PaymentRequest request = TransactionRequestTest.getDefaultRequest();

    try {
      response = connector.preparePayment(request);
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(response);
    assertNotNull(response.getMerchantToken());
    assertEquals(ResultCode.SUCCESS, response.getCode());

    PaymentStatusResponse statusResponse = null;
    PaymentStatusRequest statusRequest = new PaymentStatusRequest();
    statusRequest.setMerchantToken(response.getMerchantToken());

    try {
      statusResponse = connector.getPaymentStatus(statusRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(statusResponse);
    assertNotNull(statusResponse.getCode());
    assertEquals(ResultCode.SUCCESS, statusResponse.getCode());
    assertEquals(PaymentStatusValue.NOT_PROCESSED, statusResponse.getStatus());

    List<TransactionAttempt> transactionAttempts = statusResponse.getTransactions();
    assertNotNull(transactionAttempts);
    // API does not return not processed transaction
    assertEquals(0, transactionAttempts.size());

    // TODO: process the transaction and check updated status
  }

  /**
   * Successful transaction status request (creation + status) with an old API version
   */
  @Test
  public void transactionStatusTestSuccessfullOldApiVersion() {
    PaymentResponse response = null;
    PaymentRequest request = TransactionRequestTest.getDefaultRequest();

    try {
      response = connector.preparePayment(request);
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(response);
    assertNotNull(response.getMerchantToken());
    assertEquals(ResultCode.SUCCESS, response.getCode());

    PaymentStatusResponse statusResponse = null;
    PaymentStatusRequest statusRequest = new PaymentStatusRequest();
    statusRequest.setMerchantToken(response.getMerchantToken());
    statusRequest.setApiVersion("002");

    try {
      statusResponse = connector.getPaymentStatus(statusRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(statusResponse);
    assertNotNull(statusResponse.getCode());
    assertEquals(ResultCode.SUCCESS, statusResponse.getCode());
    assertEquals(PaymentStatusValue.NOT_PROCESSED, statusResponse.getStatus());
  }

  /**
   * Transaction status request with an invalid merchant token
   * 
   */
  @Test
  public void transactionStatusTestInvalidToken() {
    PaymentStatusResponse statusResponse = null;
    PaymentStatusRequest statusRequest = new PaymentStatusRequest();
    statusRequest.setMerchantToken("666999666");

    try {
      statusResponse = connector.getPaymentStatus(statusRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(statusResponse);
    assertNotNull(statusResponse.getCode());
    assertEquals(ResultCode.NOT_FOUND, statusResponse.getCode());

    List<TransactionAttempt> transactionAttempts = statusResponse.getTransactions();
    assertNull(transactionAttempts);
  }
}
