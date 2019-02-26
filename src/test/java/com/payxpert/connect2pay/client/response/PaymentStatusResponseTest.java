package com.payxpert.connect2pay.client.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.payxpert.connect2pay.client.containers.ChargebackPaymentMeanInfo;
import com.payxpert.connect2pay.client.containers.ReversalPaymentMeanInfo;
import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.client.containers.TransactionAttemptTest;
import com.payxpert.connect2pay.constants.PaymentStatusValue;
import com.payxpert.connect2pay.constants.PaymentMethod;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.constants.TransactionOperation;

public class PaymentStatusResponseTest {
  @Test
  public void testPaymentStatusResponse() {
    PaymentStatusResponse response = getDefaultResponse();

    assertEquals(Integer.valueOf(1012), response.getAmount());
    assertEquals(ResultCode.SUCCESS, response.getCode());
    assertEquals("{mydata:\"test\"}", response.getCtrlCustomData());
    assertEquals("EUR", response.getCurrency());
    assertEquals("Success", response.getErrorMessage());
    assertEquals("a2xta2VyKm1sw6lrbWFza2ZtKmHDqW1MS8OpbWZ6YWxmZGs", response.getMerchantToken());
    assertEquals("Transaction Attempt", response.getMessage());
    assertEquals(TransactionOperation.SALE, response.getOperation());
    assertEquals("MyOrder1234", response.getOrderId());
    assertEquals("000", response.getErrorCode());
    assertEquals(PaymentStatusValue.AUTHORIZED, response.getStatus());

    List<TransactionAttempt> transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(3, transactionAttempts.size());

    TransactionAttempt lastTransactionAttempt = response.getLastInitialTransactionAttempt().orElse(null);
    assertNotNull(lastTransactionAttempt);
    assertEquals("Transaction Attempt 2", lastTransactionAttempt.getResultMessage());

    // Add a null transaction
    response.addTransactionAttempt(null);
    transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(4, transactionAttempts.size());

    lastTransactionAttempt = response.getLastInitialTransactionAttempt().orElse(null);
    assertNotNull(lastTransactionAttempt);
    assertEquals("Transaction Attempt 2", lastTransactionAttempt.getResultMessage());

    // Add a transaction with date null
    TransactionAttempt newTransactionAttempt = new TransactionAttemptTest().getDefaultTransactionAttempt();
    newTransactionAttempt.setDate(null);
    newTransactionAttempt.setResultMessage("testTransactionStatusResponse");
    response.addTransactionAttempt(newTransactionAttempt);
    transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(5, transactionAttempts.size());

    lastTransactionAttempt = response.getLastInitialTransactionAttempt().orElse(null);
    assertNotNull(lastTransactionAttempt);
    assertEquals("Transaction Attempt 2", lastTransactionAttempt.getResultMessage());

    // Test with only transaction with date null
    response.setTransactions(null);
    response.addTransactionAttempt(newTransactionAttempt);
    transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(1, transactionAttempts.size());

    lastTransactionAttempt = response.getLastInitialTransactionAttempt().orElse(null);
    assertNotNull(lastTransactionAttempt);
    assertEquals("testTransactionStatusResponse", lastTransactionAttempt.getResultMessage());
  }

  @Test
  public void testPaymentStatusResponseWithReferral() {
    PaymentStatusResponse response = getResponseWithReferral();

    List<TransactionAttempt> transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(7, transactionAttempts.size());

    TransactionAttempt lastTransactionAttempt = response.getLastInitialTransactionAttempt().orElse(null);
    assertNotNull(lastTransactionAttempt);
    assertNull(lastTransactionAttempt.getRefTransactionId());
    assertEquals("Transaction Attempt 4", lastTransactionAttempt.getResultMessage());

    TransactionAttempt cancelTransactionAttempt = response.getTransactionAttempt(4);
    assertNotNull(cancelTransactionAttempt);
    assertNotNull(cancelTransactionAttempt.getRefTransactionId());
    assertEquals("Transaction Attempt 5", cancelTransactionAttempt.getResultMessage());
    assertEquals(lastTransactionAttempt.getTransactionId(), cancelTransactionAttempt.getRefTransactionId());
  }

  @Test
  public void testPaymentStatusResponseWithChargeback() throws Exception {
    PaymentStatusResponse response = getResponseFromJSONWithChargeback();

    assertNotNull(response);

    List<TransactionAttempt> transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(3, transactionAttempts.size());

    // The submission
    TransactionAttempt lastTransactionAttempt = response.getLastInitialTransactionAttempt().orElse(null);
    assertNotNull(lastTransactionAttempt);
    assertNull(lastTransactionAttempt.getRefTransactionId());
    assertEquals("4reazyd5ln-4v8e", lastTransactionAttempt.getTransactionId());

    // The related collection
    TransactionAttempt collectTransactionAttempt = response
        .getReferringTransactionAttempt(lastTransactionAttempt, TransactionOperation.COLLECTION).orElse(null);
    assertNotNull(collectTransactionAttempt);
    assertEquals("4reazyd5ln-4v8e", collectTransactionAttempt.getRefTransactionId());
    assertEquals("4reazyd5ln-g5ty", collectTransactionAttempt.getTransactionId());

    // The chargeback
    TransactionAttempt cbTransactionAttempt = response.getTransactionAttempt(2);
    assertNotNull(cbTransactionAttempt);
    assertEquals("4reazyd5ln-g5ty", cbTransactionAttempt.getRefTransactionId());
    assertEquals("4reazyd5ln-vgt7", cbTransactionAttempt.getTransactionId());

    ChargebackPaymentMeanInfo cbPmInfo = cbTransactionAttempt.getChargebackPaymentMeanInfo();
    assertNotNull(cbPmInfo);
    assertEquals(1517554885000L, cbPmInfo.getDate().getTime());
    assertEquals("AC01", cbPmInfo.getReasonCode());
  }

  @Test
  public void testPaymentStatusResponseWithReversal() throws Exception {
    PaymentStatusResponse response = getResponseFromJSONWithReversal();

    assertNotNull(response);

    List<TransactionAttempt> transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(3, transactionAttempts.size());

    // The submission
    TransactionAttempt lastTransactionAttempt = response.getLastInitialTransactionAttempt().orElse(null);
    assertNotNull(lastTransactionAttempt);
    assertNull(lastTransactionAttempt.getRefTransactionId());
    assertEquals("4reazyd5ln-4v8e", lastTransactionAttempt.getTransactionId());

    // The related collection
    TransactionAttempt collectTransactionAttempt = response
        .getReferringTransactionAttempt(lastTransactionAttempt, TransactionOperation.COLLECTION).orElse(null);
    assertNotNull(collectTransactionAttempt);
    assertEquals("4reazyd5ln-4v8e", collectTransactionAttempt.getRefTransactionId());
    assertEquals("4reazyd5ln-g5ty", collectTransactionAttempt.getTransactionId());

    // The reversal
    TransactionAttempt cbTransactionAttempt = response.getTransactionAttempt(2);
    assertNotNull(cbTransactionAttempt);
    assertEquals("4reazyd5ln-g5ty", cbTransactionAttempt.getRefTransactionId());
    assertEquals("4reazyd5ln-vgt7", cbTransactionAttempt.getTransactionId());

    ReversalPaymentMeanInfo revPmInfo = cbTransactionAttempt.getReversalPaymentMeanInfo();
    assertNotNull(revPmInfo);
    assertEquals(1517554885000L, revPmInfo.getDate().getTime());
    assertEquals("MD01", revPmInfo.getReasonCode());
  }

  @Test
  public void testGetReferringTransactionAttempt() {
    PaymentStatusResponse response = getResponseWithReferral();

    List<TransactionAttempt> transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(7, transactionAttempts.size());

    TransactionAttempt refTransactionAttempt = response.getTransactionAttempt("1234567-1234").orElse(null);
    assertNotNull(refTransactionAttempt);
    assertNull(refTransactionAttempt.getRefTransactionId());
    assertEquals("Transaction Attempt 4", refTransactionAttempt.getResultMessage());

    TransactionAttempt cancelTransactionAttempt = response
        .getReferringTransactionAttempt(refTransactionAttempt, TransactionOperation.CANCEL).orElse(null);
    assertNotNull(cancelTransactionAttempt);
    assertNotNull(cancelTransactionAttempt.getRefTransactionId());
    assertEquals("Transaction Attempt 5", cancelTransactionAttempt.getResultMessage());
    assertEquals(refTransactionAttempt.getTransactionId(), cancelTransactionAttempt.getRefTransactionId());
  }

  @Test
  public void testGetReferringTransactionAttempts() {
    PaymentStatusResponse response = getResponseWithReferral();

    List<TransactionAttempt> transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(7, transactionAttempts.size());

    TransactionAttempt refTransactionAttempt = response.getTransactionAttempt("1234567-1234").orElse(null);
    assertNotNull(refTransactionAttempt);
    assertNull(refTransactionAttempt.getRefTransactionId());
    assertEquals("Transaction Attempt 4", refTransactionAttempt.getResultMessage());

    List<TransactionAttempt> rebillTransactionAttempts = response.getReferringTransactionAttempts(refTransactionAttempt,
        TransactionOperation.SUBMISSION);
    assertNotNull(rebillTransactionAttempts);
    assertFalse(rebillTransactionAttempts.isEmpty());
    assertEquals(2, rebillTransactionAttempts.size());

    TransactionAttempt firstRebill = rebillTransactionAttempts.get(0);
    assertEquals("Transaction Attempt 6", firstRebill.getResultMessage());

    TransactionAttempt secondRebill = rebillTransactionAttempts.get(1);
    assertEquals("Transaction Attempt 7", secondRebill.getResultMessage());
  }

  @Test
  public void testGetTransactionAttemptByTransactionId() {
    PaymentStatusResponse response = getResponseWithReferral();

    TransactionAttempt cancelTransactionAttempt = response.getTransactionAttempt(4);
    assertNotNull(cancelTransactionAttempt);
    assertNotNull(cancelTransactionAttempt.getRefTransactionId());

    TransactionAttempt refTransactionAttempt = response
        .getTransactionAttempt(cancelTransactionAttempt.getRefTransactionId()).orElse(null);
    assertNotNull(refTransactionAttempt);
    assertNull(refTransactionAttempt.getRefTransactionId());
    assertEquals("Transaction Attempt 4", refTransactionAttempt.getResultMessage());
    assertEquals("1234567-1234", refTransactionAttempt.getTransactionId());
  }

  public static PaymentStatusResponse getDefaultResponse() {
    PaymentStatusResponse response = new PaymentStatusResponse();
    response.setAmount(1012);
    response.setCode(ResultCode.SUCCESS);
    response.setCtrlCustomData("{mydata:\"test\"}");
    response.setCurrency("EUR");
    response.setErrorMessage("Success");
    response.setMerchantToken("a2xta2VyKm1sw6lrbWFza2ZtKmHDqW1MS8OpbWZ6YWxmZGs");
    response.setMessage("Transaction Attempt");
    response.setOperation(TransactionOperation.SALE);
    response.setOrderId("MyOrder1234");
    response.setErrorCode("000");
    response.setStatus(PaymentStatusValue.AUTHORIZED);

    TransactionAttempt transactionAttempt = new TransactionAttemptTest().getDefaultTransactionAttempt();
    response.addTransactionAttempt(transactionAttempt);

    TransactionAttempt transactionAttempt2 = new TransactionAttemptTest().getDefaultTransactionAttempt();
    Calendar calendar = Calendar.getInstance();
    calendar.set(2017, 11, 11, 8, 12, 35);
    calendar.set(Calendar.MILLISECOND, 12);
    transactionAttempt2.setDate(calendar.getTime());
    transactionAttempt2.setResultMessage("Transaction Attempt 2");
    response.addTransactionAttempt(transactionAttempt2);

    TransactionAttempt transactionAttempt3 = new TransactionAttemptTest().getDefaultTransactionAttempt();
    calendar.set(2017, 10, 10, 8, 12, 35);
    transactionAttempt3.setDate(calendar.getTime());
    transactionAttempt3.setResultMessage("Transaction Attempt 3");
    response.addTransactionAttempt(transactionAttempt3);

    return response;
  }

  public static PaymentStatusResponse getResponseWithReferral() {
    PaymentStatusResponse response = getDefaultResponse();

    TransactionAttempt transactionAttempt4 = new TransactionAttemptTest().getDefaultTransactionAttempt();
    Calendar calendar = Calendar.getInstance();
    calendar.set(2017, 11, 15, 9, 16, 43);
    transactionAttempt4.setDate(calendar.getTime());
    transactionAttempt4.setResultMessage("Transaction Attempt 4");
    transactionAttempt4.setPaymentType(PaymentMethod.DIRECT_DEBIT);
    transactionAttempt4.setOperation(TransactionOperation.SUBMISSION);
    transactionAttempt4.setTransactionId("1234567-1234");
    response.addTransactionAttempt(transactionAttempt4);

    TransactionAttempt transactionAttempt5 = new TransactionAttemptTest().getDefaultTransactionAttempt();
    calendar.set(2017, 11, 16, 7, 0, 6);
    transactionAttempt5.setDate(calendar.getTime());
    transactionAttempt5.setResultMessage("Transaction Attempt 5");
    transactionAttempt5.setPaymentType(PaymentMethod.DIRECT_DEBIT);
    transactionAttempt5.setOperation(TransactionOperation.CANCEL);
    transactionAttempt5.setTransactionId("1234567-4321");
    transactionAttempt5.setRefTransactionId("1234567-1234");
    response.addTransactionAttempt(transactionAttempt5);

    // Adding them in reverse order will check the sort
    TransactionAttempt transactionAttempt7 = new TransactionAttemptTest().getDefaultTransactionAttempt();
    calendar.set(2018, 01, 12, 22, 45, 45);
    transactionAttempt7.setDate(calendar.getTime());
    transactionAttempt7.setResultMessage("Transaction Attempt 7");
    transactionAttempt7.setPaymentType(PaymentMethod.DIRECT_DEBIT);
    transactionAttempt7.setOperation(TransactionOperation.SUBMISSION);
    transactionAttempt7.setTransactionId("1234567-cv3t");
    transactionAttempt7.setRefTransactionId("1234567-1234");
    response.addTransactionAttempt(transactionAttempt7);

    TransactionAttempt transactionAttempt6 = new TransactionAttemptTest().getDefaultTransactionAttempt();
    calendar.set(2017, 12, 10, 10, 4, 8);
    transactionAttempt6.setDate(calendar.getTime());
    transactionAttempt6.setResultMessage("Transaction Attempt 6");
    transactionAttempt6.setPaymentType(PaymentMethod.DIRECT_DEBIT);
    transactionAttempt6.setOperation(TransactionOperation.SUBMISSION);
    transactionAttempt6.setTransactionId("1234567-dfg6");
    transactionAttempt6.setRefTransactionId("1234567-1234");
    response.addTransactionAttempt(transactionAttempt6);

    return response;
  }

  public static PaymentStatusResponse getResponseFromJSONWithChargeback() throws Exception {
    String json = "{\"transactions\":[{\"date\":1516448832000,\"amount\":2548,\"paymentType\":"
        + "\"DirectDebit\",\"paymentMeanInfo\":{\"bankAccount\":{\"holderName\":\"John Snow\","
        + "\"bankName\":\"BNP\",\"iban\":\"NL29ABNA050988XXXX\",\"bic\":\"ABNANL2AXXX\","
        + "\"countryCode\":\"NL\",\"sepaMandate\":{\"description\":\"Unit Test Mandate\","
        + "\"status\":\"SIGNED\",\"type\":\"RECURRENT\",\"scheme\":\"CORE\",\"signatureType\":\"SMS\","
        + "\"phoneNumber\":\"+34123456789\",\"signedAt\":1516448821000,\"createdAt\":1516448795000,"
        + "\"lastUsedAt\":1516448832000,\"downloadUrl\":\"https://fake.url/mandate/man654322/dl\"}},"
        + "\"statementDescriptor\":\"Test statement descriptor\",\"collectedAt\":1516579200000},"
        + "\"shopper\":{\"name\":\"John Snow\",\"address\":\"Calle Mallorca, 66\",\"zipcode\":\"08008\","
        + "\"city\":\"Barcelona\",\"state\":\"Barcelona\",\"countryCode\":\"ES\",\"email\":"
        + "\"dev@payxpert.com\",\"ipAddress\":\"127.0.0.1\"},\"operation\":\"submission\","
        + "\"resultCode\":\"000\",\"resultMessage\":\"Transaction successfully completed\","
        + "\"status\":\"Authorized\",\"transactionID\":\"4reazyd5ln-4v8e\"},{\"refTransactionID\":"
        + "\"4reazyd5ln-4v8e\",\"date\":1516596043000,\"amount\":2548,\"paymentType\":\"DirectDebit\","
        + "\"paymentMeanInfo\":{\"bankAccount\":{\"holderName\":\"John Snow\",\"bankName\":\"BNP\","
        + "\"iban\":\"NL29ABNA050988XXXX\",\"bic\":\"ABNANL2AXXX\",\"countryCode\":\"NL\",\"sepaMandate\":"
        + "{\"description\":\"Unit Test Mandate\",\"status\":\"SIGNED\",\"type\":\"RECURRENT\",\"scheme\":"
        + "\"CORE\",\"signatureType\":\"SMS\",\"phoneNumber\":\"+34123456789\",\"signedAt\":1516448821000,"
        + "\"createdAt\":1516448795000,\"lastUsedAt\":1516448832000,\"downloadUrl\":"
        + "\"https://fake.url/mandate/man654322/dl\"}},\"statementDescriptor\":\"Test statement descriptor\","
        + "\"collectedAt\":1516579200000},\"shopper\":{\"name\":\"John Snow\",\"address\":\"Calle Mallorca, 66\","
        + "\"zipcode\":\"08008\",\"city\":\"Barcelona\",\"state\":\"Barcelona\",\"countryCode\":\"ES\","
        + "\"email\":\"dev@payxpert.com\",\"ipAddress\":\"127.0.0.1\"},\"operation\":\"collection\","
        + "\"resultCode\":\"000\",\"resultMessage\":\"Transaction successfully completed\",\"status\":"
        + "\"Authorized\",\"transactionID\":\"4reazyd5ln-g5ty\"},{\"refTransactionID\":\"4reazyd5ln-g5ty\","
        + "\"date\":1517559172000,\"amount\":2548,\"paymentType\":\"Chargeback\",\"paymentMeanInfo\":"
        + "{\"date\":1517554885000,\"reasonCode\":\"AC01\"},\"shopper\":{\"name\":"
        + "\"John Snow\",\"address\":\"Calle Mallorca, 66\",\"zipcode\":\"08008\",\"city\":\"Barcelona\","
        + "\"state\":\"Barcelona\",\"countryCode\":\"ES\",\"email\":\"dev@payxpert.com\",\"ipAddress\":"
        + "\"127.0.0.1\"},\"resultCode\":\"000\",\"resultMessage\":\"Transaction successfully completed\","
        + "\"status\":\"Authorized\",\"transactionID\":\"4reazyd5ln-vgt7\"}],\"status\":\"Authorized\","
        + "\"merchantToken\":\"4y4-GXqzNyOLJNX8BPzUbA\",\"operation\":\"submission\",\"errorCode\":\"000\","
        + "\"errorMessage\":\"Transaction successfully completed\",\"orderID\":\"1234567E1\",\"currency\":\"EUR\",\"amount\":2548}";

    return new PaymentStatusResponse().fromJson(json);
  }

  public static PaymentStatusResponse getResponseFromJSONWithReversal() throws Exception {
    String json = "{\"transactions\":[{\"date\":1516448832000,\"amount\":2548,\"paymentType\":"
        + "\"DirectDebit\",\"paymentMeanInfo\":{\"bankAccount\":{\"holderName\":\"John Snow\","
        + "\"bankName\":\"BNP\",\"iban\":\"NL29ABNA050988XXXX\",\"bic\":\"ABNANL2AXXX\","
        + "\"countryCode\":\"NL\",\"sepaMandate\":{\"description\":\"Unit Test Mandate\","
        + "\"status\":\"SIGNED\",\"type\":\"RECURRENT\",\"scheme\":\"CORE\",\"signatureType\":\"SMS\","
        + "\"phoneNumber\":\"+34123456789\",\"signedAt\":1516448821000,\"createdAt\":1516448795000,"
        + "\"lastUsedAt\":1516448832000,\"downloadUrl\":\"https://fake.url/mandate/man654322/dl\"}},"
        + "\"statementDescriptor\":\"Test statement descriptor\",\"collectedAt\":1516579200000},"
        + "\"shopper\":{\"name\":\"John Snow\",\"address\":\"Calle Mallorca, 66\",\"zipcode\":\"08008\","
        + "\"city\":\"Barcelona\",\"state\":\"Barcelona\",\"countryCode\":\"ES\",\"email\":"
        + "\"dev@payxpert.com\",\"ipAddress\":\"127.0.0.1\"},\"operation\":\"submission\","
        + "\"resultCode\":\"000\",\"resultMessage\":\"Transaction successfully completed\","
        + "\"status\":\"Authorized\",\"transactionID\":\"4reazyd5ln-4v8e\"},{\"refTransactionID\":"
        + "\"4reazyd5ln-4v8e\",\"date\":1516596043000,\"amount\":2548,\"paymentType\":\"DirectDebit\","
        + "\"paymentMeanInfo\":{\"bankAccount\":{\"holderName\":\"John Snow\",\"bankName\":\"BNP\","
        + "\"iban\":\"NL29ABNA050988XXXX\",\"bic\":\"ABNANL2AXXX\",\"countryCode\":\"NL\",\"sepaMandate\":"
        + "{\"description\":\"Unit Test Mandate\",\"status\":\"SIGNED\",\"type\":\"RECURRENT\",\"scheme\":"
        + "\"CORE\",\"signatureType\":\"SMS\",\"phoneNumber\":\"+34123456789\",\"signedAt\":1516448821000,"
        + "\"createdAt\":1516448795000,\"lastUsedAt\":1516448832000,\"downloadUrl\":"
        + "\"https://fake.url/mandate/man654322/dl\"}},\"statementDescriptor\":\"Test statement descriptor\","
        + "\"collectedAt\":1516579200000},\"shopper\":{\"name\":\"John Snow\",\"address\":\"Calle Mallorca, 66\","
        + "\"zipcode\":\"08008\",\"city\":\"Barcelona\",\"state\":\"Barcelona\",\"countryCode\":\"ES\","
        + "\"email\":\"dev@payxpert.com\",\"ipAddress\":\"127.0.0.1\"},\"operation\":\"collection\","
        + "\"resultCode\":\"000\",\"resultMessage\":\"Transaction successfully completed\",\"status\":"
        + "\"Authorized\",\"transactionID\":\"4reazyd5ln-g5ty\"},{\"refTransactionID\":\"4reazyd5ln-g5ty\","
        + "\"date\":1517559172000,\"amount\":2548,\"paymentType\":\"Reversal\",\"paymentMeanInfo\":"
        + "{\"date\":1517554885000,\"reasonCode\":\"MD01\"},\"shopper\":{\"name\":"
        + "\"John Snow\",\"address\":\"Calle Mallorca, 66\",\"zipcode\":\"08008\",\"city\":\"Barcelona\","
        + "\"state\":\"Barcelona\",\"countryCode\":\"ES\",\"email\":\"dev@payxpert.com\",\"ipAddress\":"
        + "\"127.0.0.1\"},\"resultCode\":\"000\",\"resultMessage\":\"Transaction successfully completed\","
        + "\"status\":\"Authorized\",\"transactionID\":\"4reazyd5ln-vgt7\"}],\"status\":\"Authorized\","
        + "\"merchantToken\":\"4y4-GXqzNyOLJNX8BPzUbA\",\"operation\":\"submission\",\"errorCode\":\"000\","
        + "\"errorMessage\":\"Transaction successfully completed\",\"orderID\":\"1234567E1\",\"currency\":\"EUR\",\"amount\":2548}";

    return new PaymentStatusResponse().fromJson(json);
  }

}
