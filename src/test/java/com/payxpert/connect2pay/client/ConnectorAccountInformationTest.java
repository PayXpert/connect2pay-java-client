package com.payxpert.connect2pay.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.requests.AccountInformationRequest;
import com.payxpert.connect2pay.client.response.AccountInformationResponse;
import com.payxpert.connect2pay.client.response.PaymentMethodInformation;
import com.payxpert.connect2pay.constants.ResultCode;

public class ConnectorAccountInformationTest extends ConnectorTransactionTest {
  @Before
  public void init() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
  }

  /**
   * Account information request
   * 
   */
  @Test
  public void accountInformationTest() {
    AccountInformationResponse response = null;
    AccountInformationRequest infoRequest = new AccountInformationRequest();

    try {
      response = connector.getAccountInformation(infoRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(response);
    assertNotNull(response.getCode());
    assertEquals(ResultCode.SUCCESS, response.getCode());
    assertEquals("002.60", response.getApiVersion());

    // Don't test values in details as this is dependent on remote application configuration
    assertNotNull(response.getName());
    assertNotNull(response.getDisplayTerms());
    assertNotNull(response.getMaxAttempts());
    assertNotNull(response.getNotificationSenderName());
    assertNotNull(response.getNotificationSenderEmail());
    assertNotNull(response.getNotificationOnSuccess());
    assertNotNull(response.getNotificationOnFailure());
    assertNotNull(response.getMerchantNotification());
    assertNotNull(response.getMerchantNotificationTo());
    assertNotNull(response.getMerchantNotificationLang());

    List<PaymentMethodInformation> paymentMethods = response.getPaymentMethods();

    assertNotNull(paymentMethods);
    assertTrue(paymentMethods.size() > 0);
  }
}
