package com.payxpert.connect2pay.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.Connect2payClient;
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
    Assert.assertEquals(TransactionStatusValue.NOT_PROCESSED, statusResponse.getStatus());
    Assert.assertEquals(PaymentType.CREDIT_CARD, statusResponse.getPaymentType());
    

    Assert.assertEquals("ID12345", statusResponse.getShopperIDNumber());
    Assert.assertEquals("19700101", statusResponse.getShopperBirthDate());
    
    Assert.assertNotNull(statusResponse.getCCPaymentMeanInfo());
    Assert.assertEquals("Bernard Ménez", statusResponse.getCCPaymentMeanInfo().getCardHolderName());
    
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
    Assert.assertNull(statusResponse.getTransactionId());
  }
}
