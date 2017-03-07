package com.payxpert.connect2pay.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.payxpert.connect2pay.client.containers.CreditCardPaymentMeanInfo;
import com.payxpert.connect2pay.client.response.TransactionStatusResponse;
import com.payxpert.connect2pay.constants.PaymentType;
import com.payxpert.connect2pay.constants.ResultCode;

public class ConnectorHandleCallbackTest extends ConnectorTransactionTest {
  @Before
  public void init() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
  }

  /**
   * Successful callback data handling
   */
  @Test
  public void handleCallbackTestSuccessfull() {
    String receivedData = "{\"paymentMeanInfo\":{\"cardNumber\":\"411111XXXXXX1111\",\"cardExpireYear\":\"2024\""
        + ",\"cardExpireMonth\":\"10\",\"cardHolderName\":\"Tech Payxpert\",\"cardBrand\":\"VISA\""
        + ",\"cardLevel\":\"other\",\"cardSubType\":\"credit\",\"iinCountry\":\"US\""
        + ",\"iinBankName\":\"jpmorgan chase bank na\",\"is3DSecure\":false}"
        + ",\"ctrlCustomData\":\"Give that back to me please !!\",\"status\":\"Authorized\","
        + "\"merchantToken\":\"2NmIsxITW2hrzAowK44nZA\",\"transactionID\":\"18253\",\"paymentType\":\"CreditCard\""
        + ",\"operation\":\"sale\",\"errorCode\":\"000\",\"errorMessage\":\"Transaction successfully completed\","
        + "\"orderID\":\"2014-04-02-14.32.45\",\"currency\":\"EUR\",\"amount\":1000,\"shopperName\":\"Tech Payxpert\","
        + "\"shopperAddress\":\"NA\",\"shopperZipcode\":\"NA\",\"shopperCity\":\"NA\",\"shopperState\":\"null\","
        + "\"shopperCountryCode\":\"FR\",\"shopperPhone\":\"+34666666666\",\"shopperEmail\":\"support@payxpert.com\","
        + "\"shopperIPAddress\":\"127.0.0.1\",\"statementDescriptor\":\"\",\"cardHolderName\":\"Tech Payxpert\"}";

    TransactionStatusResponse response = null;

    try {
      response = connector.handleCallbackStatus(receivedData);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(response);
    Assert.assertNotNull(response.getMerchantToken());
    Assert.assertEquals("2NmIsxITW2hrzAowK44nZA", response.getMerchantToken());
    Assert.assertEquals(ResultCode.SUCCESS, response.getCode());
    Assert.assertEquals("000", response.getErrorCode());
    Assert.assertEquals("Tech Payxpert", response.getShopperName());
    Assert.assertEquals("support@payxpert.com", response.getShopperEmail());
    Assert.assertEquals(PaymentType.CREDIT_CARD, response.getPaymentType());
    Assert.assertEquals("18253", response.getTransactionId());
    CreditCardPaymentMeanInfo pmInfo = response.getCCPaymentMeanInfo();
    Assert.assertNotNull(pmInfo);
    Assert.assertEquals("411111XXXXXX1111", pmInfo.getCardNumber());
    Assert.assertEquals("2024", pmInfo.getCardExpireYear());
    Assert.assertEquals("10", pmInfo.getCardExpireMonth());
    Assert.assertEquals("Tech Payxpert", pmInfo.getCardHolderName());
    Assert.assertEquals("VISA", pmInfo.getCardBrand());
    Assert.assertEquals("other", pmInfo.getCardLevel());
    Assert.assertEquals("credit", pmInfo.getCardSubType());
    Assert.assertEquals("US", pmInfo.getIinCountry());
    Assert.assertEquals("jpmorgan chase bank na", pmInfo.getIinBankName());
    Assert.assertFalse(pmInfo.getIs3DSecure());
  }

  /**
   * Callback data handling with incorrect value
   * 
   * @throws Exception
   * 
   */
  @Test(expected = InvalidFormatException.class)
  public void handleCallbackTestInvalidData() throws Exception {
    String receivedData = "{\"ctrlCustomData\":\"Give that back to me please !!\",\"status\":\"Authorized\","
        + "\"merchantToken\":\"2NmIsxITW2hrzAowK44nZA\",\"transactionID\":\"18253\",\"paymentType\":\"CreditCardZZZ\""
        + ",\"operation\":\"sale\",\"errorCode\":\"000\",\"errorMessage\":\"Transaction successfully completed\","
        + "\"orderID\":\"2014-04-02-14.32.45\",\"currency\":\"EUR\",\"amount\":1000,\"shopperName\":\"Tech Payxpert\","
        + "\"shopperAddress\":\"NA\",\"shopperZipcode\":\"NA\",\"shopperCity\":\"NA\",\"shopperState\":\"null\","
        + "\"shopperCountryCode\":\"FR\",\"shopperPhone\":\"+34666666666\",\"shopperEmail\":\"support@payxpert.com\","
        + "\"shopperIPAddress\":\"127.0.0.1\",\"statementDescriptor\":\"\",\"cardHolderName\":\"Tech Payxpert\"}";

    connector.handleCallbackStatus(receivedData);
  }
}
