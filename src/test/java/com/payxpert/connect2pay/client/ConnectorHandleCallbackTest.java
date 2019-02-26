package com.payxpert.connect2pay.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.payxpert.connect2pay.client.containers.BankAccount;
import com.payxpert.connect2pay.client.containers.BankTransferPaymentMeanInfo;
import com.payxpert.connect2pay.client.containers.CreditCardPaymentMeanInfo;
import com.payxpert.connect2pay.client.containers.Shopper;
import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.client.response.PaymentStatusResponse;
import com.payxpert.connect2pay.constants.PaymentMethod;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.constants.TransactionOperation;
import com.payxpert.connect2pay.constants.PaymentStatusValue;

public class ConnectorHandleCallbackTest extends ConnectorTransactionTest {
  @Before
  public void init() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
  }

  /**
   * Successful callback data handling
   */
  @Test
  public void handleCallbackCreditTestSuccessfull() {
    StringBuilder receivedData = new StringBuilder();
    receivedData.append('{');
    receivedData.append("\"merchantToken\":").append("\"2NmIsxITW2hrzAowK44nZA\"");
    receivedData.append(",\"operation\":").append("\"sale\"");
    receivedData.append(",\"errorCode\":").append("\"000\"");
    receivedData.append(",\"errorMessage\":").append("\"Transaction successfully completed\"");
    receivedData.append(",\"status\":").append("\"Authorized\"");
    receivedData.append(",\"ctrlCustomData\":").append("\"Give that back to me please !!\"");
    receivedData.append(",\"orderID\":").append("\"2014-04-02-14.32.45\"");
    receivedData.append(",\"currency\":").append("\"EUR\"");
    receivedData.append(",\"amount\":").append("1000");

    receivedData.append(",\"transactions\":[");
    receivedData.append('{');
    receivedData.append("\"paymentType\":").append("\"CreditCard\"");
    receivedData.append(",\"paymentMeanInfo\":");
    receivedData.append('{');
    receivedData.append("\"cardNumber\":").append("\"411111XXXXXX1111\"");
    receivedData.append(",\"cardExpireYear\":").append("\"2024\"");
    receivedData.append(",\"cardExpireMonth\":").append("\"10\"");
    receivedData.append(",\"cardHolderName\":").append("\"Tech Payxpert\"");
    receivedData.append(",\"cardBrand\":").append("\"VISA\"");
    receivedData.append(",\"cardLevel\":").append("\"other\"");
    receivedData.append(",\"cardSubType\":").append("\"credit\"");
    receivedData.append(",\"iinCountry\":").append("\"US\"");
    receivedData.append(",\"iinBankName\":").append("\"jpmorgan chase bank na\"");
    receivedData.append(",\"is3DSecure\":").append("false");
    receivedData.append(",\"statementDescriptor\":").append("\"My statement\"");
    receivedData.append('}');

    receivedData.append(",\"shopper\":");
    receivedData.append('{');

    receivedData.append("\"name\":").append("\"Tech Payxpert\"");
    receivedData.append(",\"address\":").append("\"NA\"");
    receivedData.append(",\"zipcode\":").append("\"NA\"");
    receivedData.append(",\"city\":").append("\"Barcelona\"");
    receivedData.append(",\"state\":").append("\"barcelona\"");
    receivedData.append(",\"countryCode\":").append("\"FR\"");
    receivedData.append(",\"phone\":").append("\"+34666666666\"");
    receivedData.append(",\"email\":").append("\"support@payxpert.com\"");
    receivedData.append(",\"birthDate\":").append("\"20160808\"");
    receivedData.append(",\"idNumber\":").append("\"ABC123456\"");
    receivedData.append(",\"ipAddress\":").append("\"127.0.0.1\"");
    receivedData.append('}');

    receivedData.append(",\"operation\":").append("\"sale\"");
    receivedData.append(",\"resultCode\":").append("\"000\"");
    receivedData.append(",\"resultMessage\":").append("\"Transaction successfully completed\"");
    receivedData.append(",\"status\":").append("\"Authorized\"");

    receivedData.append(",\"transactionID\":").append("\"18253\"");
    receivedData.append(",\"subscriptionID\":").append("null");
    receivedData.append('}');
    receivedData.append(']');
    receivedData.append('}');

    PaymentStatusResponse response = null;
    try {
      response = connector.handleCallbackStatus(receivedData.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(response);
    assertNotNull(response.getMerchantToken());

    assertEquals(ResultCode.SUCCESS, response.getCode());
    assertNull(response.getMessage());

    assertEquals("2NmIsxITW2hrzAowK44nZA", response.getMerchantToken());
    assertEquals("000", response.getErrorCode());
    assertEquals("Transaction successfully completed", response.getErrorMessage());

    assertEquals(TransactionOperation.SALE, response.getOperation());
    assertEquals(PaymentStatusValue.AUTHORIZED, response.getStatus());
    assertEquals("Give that back to me please !!", response.getCtrlCustomData());
    assertEquals("2014-04-02-14.32.45", response.getOrderId());

    assertEquals("EUR", response.getCurrency());
    assertEquals(Integer.valueOf(1000), response.getAmount());
    assertEquals("2014-04-02-14.32.45", response.getOrderId());

    List<TransactionAttempt> transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(1, transactionAttempts.size());

    TransactionAttempt transactionAttempt = transactionAttempts.get(0);
    assertNotNull(transactionAttempt);

    assertEquals(PaymentMethod.CREDIT_CARD, transactionAttempt.getPaymentType());
    assertEquals("18253", transactionAttempt.getTransactionId());
    assertEquals(TransactionOperation.SALE, transactionAttempt.getOperation());
    assertEquals("000", transactionAttempt.getResultCode());
    assertEquals("Transaction successfully completed", transactionAttempt.getResultMessage());
    assertEquals(PaymentStatusValue.AUTHORIZED, transactionAttempt.getStatus());
    assertEquals("18253", transactionAttempt.getTransactionId());
    assertNull(transactionAttempt.getSubscriptionId());

    CreditCardPaymentMeanInfo pmInfo = transactionAttempt.getCreditCardPaymentMeanInfo();
    assertNotNull(pmInfo);
    assertEquals("411111XXXXXX1111", pmInfo.getCardNumber());
    assertEquals("2024", pmInfo.getCardExpireYear());
    assertEquals("10", pmInfo.getCardExpireMonth());
    assertEquals("Tech Payxpert", pmInfo.getCardHolderName());
    assertEquals("VISA", pmInfo.getCardBrand());
    assertEquals("other", pmInfo.getCardLevel());
    assertEquals("credit", pmInfo.getCardSubType());
    assertEquals("US", pmInfo.getIinCountry());
    assertEquals("jpmorgan chase bank na", pmInfo.getIinBankName());
    assertEquals("My statement", pmInfo.getStatementDescriptor());
    assertFalse(pmInfo.getIs3DSecure());

    Shopper shopper = transactionAttempt.getShopper();
    assertNotNull(shopper);

    assertEquals("Tech Payxpert", shopper.getName());
    assertEquals("NA", shopper.getAddress());
    assertEquals("NA", shopper.getZipcode());
    assertEquals("Barcelona", shopper.getCity());
    assertEquals("barcelona", shopper.getState());
    assertEquals("FR", shopper.getCountryCode());
    assertEquals("+34666666666", shopper.getPhone());
    assertEquals("support@payxpert.com", shopper.getEmail());
    assertEquals("20160808", shopper.getBirthDate());
    assertEquals("ABC123456", shopper.getIdNumber());
    assertEquals("127.0.0.1", shopper.getIpAddress());
  }

  /**
   * Successful callback data handling
   */
  @Test
  public void handleCallbackBankTransferTestSuccessfull() {
    StringBuilder receivedData = new StringBuilder();
    receivedData.append('{');
    receivedData.append("\"merchantToken\":").append("\"2NmIsxITW2hrzAowK44nZA\"");
    receivedData.append(",\"operation\":").append("\"sale\"");
    receivedData.append(",\"errorCode\":").append("\"000\"");
    receivedData.append(",\"errorMessage\":").append("\"Transaction successfully completed\"");
    receivedData.append(",\"status\":").append("\"Authorized\"");
    receivedData.append(",\"ctrlCustomData\":").append("\"Give that back to me please !!\"");
    receivedData.append(",\"orderID\":").append("\"2014-04-02-14.32.45\"");
    receivedData.append(",\"currency\":").append("\"EUR\"");
    receivedData.append(",\"amount\":").append("1000");

    receivedData.append(",\"transactions\":[");
    receivedData.append('{');
    receivedData.append("\"paymentType\":").append("\"BankTransfer\"");
    receivedData.append(",\"paymentMeanInfo\":");
    receivedData.append('{');
    receivedData.append("\"sender\":");
    receivedData.append('{');
    receivedData.append("\"holderName\":").append("\"Henry\"");
    receivedData.append(",\"bankName\":").append("\"Bnp\"");
    receivedData.append(",\"iban\":").append("\"FR 2024 554 5454545 45\"");
    receivedData.append(",\"bic\":").append("\"TFEE4445454\"");
    receivedData.append(",\"countryCode\":").append("\"FR\"");
    receivedData.append('}');
    receivedData.append(",\"recipient\":");
    receivedData.append('{');
    receivedData.append("\"holderName\":").append("\"Guibet\"");
    receivedData.append(",\"bankName\":").append("\"HSBC\"");
    receivedData.append(",\"iban\":").append("\"UK 2555 2555 6655 4545\"");
    receivedData.append(",\"bic\":").append("\"ERDD545454\"");
    receivedData.append(",\"countryCode\":").append("\"UK\"");
    receivedData.append('}');
    receivedData.append('}');

    receivedData.append(",\"shopper\":");
    receivedData.append('{');
    receivedData.append("\"name\":").append("\"Tech Payxpert\"");
    receivedData.append(",\"address\":").append("\"NA\"");
    receivedData.append(",\"zipcode\":").append("\"NA\"");
    receivedData.append(",\"city\":").append("\"Barcelona\"");
    receivedData.append(",\"state\":").append("\"barcelona\"");
    receivedData.append(",\"countryCode\":").append("\"FR\"");
    receivedData.append(",\"phone\":").append("\"+34666666666\"");
    receivedData.append(",\"email\":").append("\"support@payxpert.com\"");
    receivedData.append(",\"birthDate\":").append("\"20160808\"");
    receivedData.append(",\"idNumber\":").append("\"ABC123456\"");
    receivedData.append(",\"ipAddress\":").append("\"127.0.0.1\"");
    receivedData.append('}');

    receivedData.append(",\"operation\":").append("\"sale\"");
    receivedData.append(",\"resultCode\":").append("\"000\"");
    receivedData.append(",\"resultMessage\":").append("\"Transaction successfully completed\"");
    receivedData.append(",\"status\":").append("\"Authorized\"");

    receivedData.append(",\"transactionID\":").append("\"18253\"");
    receivedData.append(",\"subscriptionID\":").append("null");
    receivedData.append('}');
    receivedData.append(']');
    receivedData.append('}');

    PaymentStatusResponse response = null;
    try {
      response = connector.handleCallbackStatus(receivedData.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(response);
    assertNotNull(response.getMerchantToken());

    assertEquals(ResultCode.SUCCESS, response.getCode());
    assertNull(response.getMessage());

    assertEquals("2NmIsxITW2hrzAowK44nZA", response.getMerchantToken());
    assertEquals("000", response.getErrorCode());
    assertEquals("Transaction successfully completed", response.getErrorMessage());

    assertEquals(TransactionOperation.SALE, response.getOperation());
    assertEquals(PaymentStatusValue.AUTHORIZED, response.getStatus());
    assertEquals("Give that back to me please !!", response.getCtrlCustomData());
    assertEquals("2014-04-02-14.32.45", response.getOrderId());

    assertEquals("EUR", response.getCurrency());
    assertEquals(Integer.valueOf(1000), response.getAmount());
    assertEquals("2014-04-02-14.32.45", response.getOrderId());

    List<TransactionAttempt> transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(1, transactionAttempts.size());

    TransactionAttempt transactionAttempt = transactionAttempts.get(0);
    assertNotNull(transactionAttempt);

    assertEquals(PaymentMethod.BANK_TRANSFER, transactionAttempt.getPaymentType());
    assertEquals("18253", transactionAttempt.getTransactionId());
    assertEquals(TransactionOperation.SALE, transactionAttempt.getOperation());
    assertEquals("000", transactionAttempt.getResultCode());
    assertEquals("Transaction successfully completed", transactionAttempt.getResultMessage());
    assertEquals(PaymentStatusValue.AUTHORIZED, transactionAttempt.getStatus());
    assertEquals("18253", transactionAttempt.getTransactionId());
    assertNull(transactionAttempt.getSubscriptionId());

    BankTransferPaymentMeanInfo pmInfo = transactionAttempt.getBankTransferPaymentMeanInfo();
    assertNotNull(pmInfo);

    BankAccount senderBankAccount = pmInfo.getSender();
    assertNotNull(senderBankAccount);

    assertEquals("Henry", senderBankAccount.getHolderName());
    assertEquals("Bnp", senderBankAccount.getBankName());
    assertEquals("FR 2024 554 5454545 45", senderBankAccount.getIban());
    assertEquals("TFEE4445454", senderBankAccount.getBic());
    assertEquals("FR", senderBankAccount.getCountryCode());

    BankAccount recipientBankAccount = pmInfo.getRecipient();
    assertNotNull(recipientBankAccount);
    assertEquals("Guibet", recipientBankAccount.getHolderName());
    assertEquals("HSBC", recipientBankAccount.getBankName());
    assertEquals("UK 2555 2555 6655 4545", recipientBankAccount.getIban());
    assertEquals("ERDD545454", recipientBankAccount.getBic());
    assertEquals("UK", recipientBankAccount.getCountryCode());

    Shopper shopper = transactionAttempt.getShopper();
    assertNotNull(shopper);

    assertEquals("Tech Payxpert", shopper.getName());
    assertEquals("NA", shopper.getAddress());
    assertEquals("NA", shopper.getZipcode());
    assertEquals("Barcelona", shopper.getCity());
    assertEquals("barcelona", shopper.getState());
    assertEquals("FR", shopper.getCountryCode());
    assertEquals("+34666666666", shopper.getPhone());
    assertEquals("support@payxpert.com", shopper.getEmail());
    assertEquals("20160808", shopper.getBirthDate());
    assertEquals("ABC123456", shopper.getIdNumber());
    assertEquals("127.0.0.1", shopper.getIpAddress());
  }

  /**
   * Callback data handling with incorrect value
   * 
   * @throws Exception
   * 
   */
  @Test(expected = InvalidFormatException.class)
  public void handleCallbackTestInvalidData() throws Exception {
    StringBuilder receivedData = new StringBuilder();
    receivedData.append('{');
    receivedData.append("\"merchantToken\":").append("\"2NmIsxITW2hrzAowK44nZA\"");
    receivedData.append(",\"operation\":").append("\"sale\"");
    receivedData.append(",\"errorCode\":").append("\"000\"");
    receivedData.append(",\"errorMessage\":").append("\"Transaction successfully completed\"");
    receivedData.append(",\"status\":").append("\"Authorized\"");
    receivedData.append(",\"ctrlCustomData\":").append("\"Give that back to me please !!\"");
    receivedData.append(",\"orderID\":").append("\"2014-04-02-14.32.45\"");
    receivedData.append(",\"currency\":").append("\"EUR\"");
    receivedData.append(",\"amount\":").append("1000");

    receivedData.append(",\"transactions\":[");
    receivedData.append('{');
    receivedData.append("\"paymentType\":").append("\"CreditCardZZZ\"");
    receivedData.append(",\"paymentMeanInfo\":");
    receivedData.append('{');
    receivedData.append("\"cardNumber\":").append("\"411111XXXXXX1111\"");
    receivedData.append(",\"cardExpireYear\":").append("\"2024\"");
    receivedData.append(",\"cardExpireMonth\":").append("\"10\"");
    receivedData.append(",\"cardHolderName\":").append("\"Tech Payxpert\"");
    receivedData.append(",\"cardBrand\":").append("\"VISA\"");
    receivedData.append(",\"cardLevel\":").append("\"other\"");
    receivedData.append(",\"cardSubType\":").append("\"credit\"");
    receivedData.append(",\"iinCountry\":").append("\"US\"");
    receivedData.append(",\"iinBankName\":").append("\"jpmorgan chase bank na\"");
    receivedData.append(",\"is3DSecure\":").append("false");
    receivedData.append(",\"statementDescriptor\":").append("\"My statement\"");
    receivedData.append('}');

    receivedData.append(",\"shopper\":");
    receivedData.append('{');

    receivedData.append("\"name\":").append("\"Tech Payxpert\"");
    receivedData.append(",\"address\":").append("\"NA\"");
    receivedData.append(",\"zipcode\":").append("\"NA\"");
    receivedData.append(",\"city\":").append("\"Barcelona\"");
    receivedData.append(",\"state\":").append("\"barcelona\"");
    receivedData.append(",\"countryCode\":").append("\"FR\"");
    receivedData.append(",\"phone\":").append("\"+34666666666\"");
    receivedData.append(",\"email\":").append("\"support@payxpert.com\"");
    receivedData.append(",\"birthDate\":").append("\"20160808\"");
    receivedData.append(",\"idNumber\":").append("\"ABC123456\"");
    receivedData.append(",\"ipAddress\":").append("\"127.0.0.1\"");
    receivedData.append('}');

    receivedData.append(",\"operation\":").append("\"sale\"");
    receivedData.append(",\"resultCode\":").append("\"000\"");
    receivedData.append(",\"resultMessage\":").append("\"Transaction successfully completed\"");
    receivedData.append(",\"status\":").append("\"Authorized\"");

    receivedData.append(",\"transactionID\":").append("\"18253\"");
    receivedData.append(",\"subscriptionID\":").append("null");
    receivedData.append('}');
    receivedData.append(']');
    receivedData.append('}');

    connector.handleCallbackStatus(receivedData.toString());
  }
}
