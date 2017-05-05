package com.payxpert.connect2pay.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.containers.Shopper;
import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.client.requests.TransactionRequest;
import com.payxpert.connect2pay.client.requests.TransactionRequestTest;
import com.payxpert.connect2pay.client.requests.TransactionStatusRequest;
import com.payxpert.connect2pay.client.response.TransactionResponse;
import com.payxpert.connect2pay.client.response.TransactionStatusResponse;
import com.payxpert.connect2pay.constants.PaymentType;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.constants.TransactionStatusValue;

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
    TransactionResponse response = null;
    TransactionRequest request = TransactionRequestTest.getDefaultRequest();

    try {
      response = connector.prepareTransaction(request);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getMerchantToken());
    Assert.assertEquals(ResultCode.SUCCESS, response.getCode());

    TransactionStatusResponse statusResponse = null;
    TransactionStatusRequest statusRequest = new TransactionStatusRequest();
    statusRequest.setMerchantToken(response.getMerchantToken());

    try {
      statusResponse = connector.getTransactionStatus(statusRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(statusResponse);
    Assert.assertNotNull(statusResponse.getCode());
    Assert.assertEquals(ResultCode.SUCCESS, statusResponse.getCode());

    List<TransactionAttempt> transactionAttempts = statusResponse.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(1, transactionAttempts.size());

    TransactionAttempt transactionAttempt = transactionAttempts.get(0);

    Assert.assertEquals(TransactionStatusValue.NOT_PROCESSED, transactionAttempt.getStatus());
    Assert.assertEquals(PaymentType.CREDIT_CARD, transactionAttempt.getPaymentType());

    Shopper shopper = transactionAttempt.getShopper();
    assertNotNull(shopper);

    Assert.assertEquals("ID12345", shopper.getIdNumber());
    Assert.assertEquals("19700101", shopper.getBirthDate());

    Assert.assertNotNull(transactionAttempt.getCCPaymentMeanInfo());
    Assert.assertEquals("Bernard Ménez", transactionAttempt.getCCPaymentMeanInfo().getCardHolderName());

  }

  /**
   * Transaction status request with an invalid merchant token
   * 
   */
  @Test
  public void transactionStatusTestInvalidToken() {
    TransactionStatusResponse statusResponse = null;
    TransactionStatusRequest statusRequest = new TransactionStatusRequest();
    statusRequest.setMerchantToken("666999666");

    try {
      statusResponse = connector.getTransactionStatus(statusRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(statusResponse);
    Assert.assertNotNull(statusResponse.getCode());
    Assert.assertEquals(ResultCode.NOT_FOUND, statusResponse.getCode());

    List<TransactionAttempt> transactionAttempts = statusResponse.getTransactions();
    assertNull(transactionAttempts);
    assertEquals(0, transactionAttempts.size());

  }
}
