package com.payxpert.connect2pay.client.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.payxpert.connect2pay.constants.PaymentMethod;

public class AccountInformationResponseTest {
  @Test
  public void testAccountInformationResponse() throws Exception {
    String json = "{\"apiVersion\":\"002.60\",\"name\":\"Unit Test Website\",\"displayTerms\":false,"
        + "\"termsUrl\":\"\",\"supportUrl\":\"http://example.com/support\",\"maxAttempts\":1,\"notificationSenderName\":\"PayXpert Payment Service\","
        + "\"notificationSenderEmail\":\"support@payxpert.com\",\"notificationOnSuccess\":true,"
        + "\"notificationOnFailure\":false,\"merchantNotification\":true,\"merchantNotificationTo\":\"support@payxpert.com\","
        + "\"merchantNotificationLang\":\"fr\"," + "\"paymentMethods\":["
        + "{\"paymentMethod\":\"BankTransfer\",\"paymentNetwork\":\"eps\",\"currencies\":[\"EUR\"],\"defaultOperation\":\"sale\"},"
        + "{\"paymentMethod\":\"DirectDebit\",\"currencies\":[\"EUR\"],\"defaultOperation\":\"submission\"},"
        + "{\"paymentMethod\":\"Line\",\"currencies\":[\"TWD\"],\"defaultOperation\":\"sale\"},"
        + "{\"paymentMethod\":\"WeChat\",\"currencies\":[\"USD\",\"EUR\"],\"defaultOperation\":\"sale\"},"
        + "{\"paymentMethod\":\"BankTransfer\",\"paymentNetwork\":\"ideal\",\"currencies\":[\"EUR\"],\"defaultOperation\":\"sale\"},"
        + "{\"paymentMethod\":\"BankTransfer\",\"paymentNetwork\":\"dragonpay\",\"currencies\":[\"EUR\"],\"defaultOperation\":\"sale\"},"
        + "{\"paymentMethod\":\"BankTransfer\",\"paymentNetwork\":\"poli\",\"currencies\":[\"EUR\"],\"defaultOperation\":\"sale\"},"
        + "{\"paymentMethod\":\"BankTransfer\",\"paymentNetwork\":\"przelewy24\",\"currencies\":[\"PLN\"],\"defaultOperation\":\"sale\"},"
        + "{\"paymentMethod\":\"ToditoCash\",\"currencies\":[\"MXN\",\"EUR\"],\"defaultOperation\":\"sale\"},"
        + "{\"paymentMethod\":\"CreditCard\",\"currencies\":[\"EUR\",\"USD\"],\"defaultOperation\":\"authorize\",\"options\":[{\"name\":\"3dsMode\",\"value\":\"honor\"}]},"
        + "{\"paymentMethod\":\"BankTransfer\",\"paymentNetwork\":\"sofort\",\"currencies\":[\"EUR\"],\"defaultOperation\":\"sale\"},"
        + "{\"paymentMethod\":\"Alipay\",\"currencies\":[\"USD\",\"EUR\"],\"defaultOperation\":\"sale\"},"
        + "{\"paymentMethod\":\"BankTransfer\",\"paymentNetwork\":\"giropay\",\"currencies\":[\"EUR\"],\"defaultOperation\":\"sale\"}]}";

    AccountInformationResponse response = new AccountInformationResponse().fromJson(json);
    assertNotNull(response);
    assertEquals("002.60", response.getApiVersion());
    assertEquals("Unit Test Website", response.getName());
    assertFalse(response.getDisplayTerms());
    assertEquals("http://example.com/support", response.getSupportUrl());
    assertEquals(new Integer(1), response.getMaxAttempts());
    assertEquals("PayXpert Payment Service", response.getNotificationSenderName());
    assertEquals("support@payxpert.com", response.getNotificationSenderEmail());
    assertTrue(response.getNotificationOnSuccess());
    assertFalse(response.getNotificationOnFailure());
    assertTrue(response.getMerchantNotification());
    assertEquals("support@payxpert.com", response.getMerchantNotificationTo());
    assertEquals("fr", response.getMerchantNotificationLang());

    List<PaymentMethodInformation> paymentMethods = response.getPaymentMethods();

    assertNotNull(paymentMethods);
    assertEquals(13, paymentMethods.size());

    PaymentMethodInformation ccMethod = null;
    for (PaymentMethodInformation paymentMethod : paymentMethods) {
      if (PaymentMethod.CREDIT_CARD.equals(paymentMethod.getPaymentMethod())) {
        ccMethod = paymentMethod;
      }
    }

    assertNotNull(ccMethod);
    assertNull(ccMethod.getPaymentNetwork());
    assertEquals("authorize", ccMethod.getDefaultOperation());
    assertEquals(2, ccMethod.getCurrencies().size());
    assertTrue(ccMethod.getCurrencies().contains("EUR"));
    assertTrue(ccMethod.getCurrencies().contains("USD"));

    List<PaymentMethodOption> pmOptions = ccMethod.getOptions();
    assertNotNull(pmOptions);
    assertEquals(1, pmOptions.size());
    assertEquals("3dsMode", pmOptions.get(0).getName());
    assertEquals("honor", pmOptions.get(0).getValue());
  }
}
