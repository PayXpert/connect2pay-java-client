package com.payxpert.connect2pay.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.client.requests.PaymentRequest;
import com.payxpert.connect2pay.client.requests.PaymentStatusRequest;
import com.payxpert.connect2pay.client.requests.PaymentRequestTest;
import com.payxpert.connect2pay.client.response.PaymentResponse;
import com.payxpert.connect2pay.client.response.PaymentStatusResponse;
import com.payxpert.connect2pay.constants.PaymentStatusValue;
import com.payxpert.connect2pay.constants.ResultCode;

public class ConnectorPaymentStatusTest extends ConnectorTransactionTest {
  @Before
  public void init() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
  }

  /**
   * Successful payment status request (creation + status)
   */
  @Test
  public void paymentStatusTestSuccessfull() {
    PaymentResponse response = null;
    PaymentRequest request = PaymentRequestTest.getDefaultRequest();

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
   * Successful payment status request (creation + status) with an old API version
   */
  @Test
  public void paymentStatusTestSuccessfullOldApiVersion() {
    PaymentResponse response = null;
    PaymentRequest request = PaymentRequestTest.getDefaultRequest();

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
   * Payment status request with an invalid merchant token
   * 
   */
  @Test
  public void paymentStatusTestInvalidToken() {
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
