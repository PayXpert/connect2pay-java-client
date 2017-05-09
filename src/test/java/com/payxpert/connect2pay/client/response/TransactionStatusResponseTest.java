package com.payxpert.connect2pay.client.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.client.containers.TransactionAttemptTest;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.constants.TransactionOperation;
import com.payxpert.connect2pay.constants.TransactionStatusValue;

public class TransactionStatusResponseTest {
  @Test
  public void testTransactionStatusResponse() {
    TransactionStatusResponse response = getDefaultRequest();

    assertEquals(Integer.valueOf(1012), response.getAmount());
    assertEquals(ResultCode.SUCCESS, response.getCode());
    assertEquals("{mydata:\"test\"}", response.getCtrlCustomData());
    assertEquals("EUR", response.getCurrency());
    assertEquals("Success", response.getErrorMessage());
    assertEquals("a2xta2VyKm1sw6lrbWFza2ZtKmHDqW1MS8OpbWZ6YWxmZGs", response.getMerchantToken());
    assertEquals("My message", response.getMessage());
    assertEquals(TransactionOperation.SALE, response.getOperation());
    assertEquals("MyOrder1234", response.getOrderId());
    assertEquals("000", response.getErrorCode());
    assertEquals(TransactionStatusValue.AUTHORIZED, response.getStatus());

    List<TransactionAttempt> transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(3, transactionAttempts.size());

    TransactionAttempt lastTransactionAttempt = response.getLastTransactionAttempt();
    assertNotNull(lastTransactionAttempt);
    assertEquals("My message 2", lastTransactionAttempt.getResultMessage());

    // Add a null transaction
    response.addTransactionAttempt(null);
    transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(4, transactionAttempts.size());

    lastTransactionAttempt = response.getLastTransactionAttempt();
    assertNotNull(lastTransactionAttempt);
    assertEquals("My message 2", lastTransactionAttempt.getResultMessage());

    // Add a transaction with date null
    TransactionAttempt newTransactionAttempt = new TransactionAttemptTest().getDefaultTransactionAttempt();
    newTransactionAttempt.setDate(null);
    newTransactionAttempt.setResultMessage("testTransactionStatusResponse");
    response.addTransactionAttempt(newTransactionAttempt);
    transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(5, transactionAttempts.size());

    lastTransactionAttempt = response.getLastTransactionAttempt();
    assertNotNull(lastTransactionAttempt);
    assertEquals("My message 2", lastTransactionAttempt.getResultMessage());

    // Test with only transaction with date null
    response.setTransactions(null);
    response.addTransactionAttempt(newTransactionAttempt);
    transactionAttempts = response.getTransactions();
    assertNotNull(transactionAttempts);
    assertEquals(1, transactionAttempts.size());

    lastTransactionAttempt = response.getLastTransactionAttempt();
    assertNotNull(lastTransactionAttempt);
    assertEquals("testTransactionStatusResponse", lastTransactionAttempt.getResultMessage());
  }

  public static TransactionStatusResponse getDefaultRequest() {
    TransactionStatusResponse reponse = new TransactionStatusResponse();
    reponse.setAmount(1012);
    reponse.setCode(ResultCode.SUCCESS);
    reponse.setCtrlCustomData("{mydata:\"test\"}");
    reponse.setCurrency("EUR");
    reponse.setErrorMessage("Success");
    reponse.setMerchantToken("a2xta2VyKm1sw6lrbWFza2ZtKmHDqW1MS8OpbWZ6YWxmZGs");
    reponse.setMessage("My message");
    reponse.setOperation(TransactionOperation.SALE);
    reponse.setOrderId("MyOrder1234");
    reponse.setErrorCode("000");
    reponse.setStatus(TransactionStatusValue.AUTHORIZED);

    TransactionAttempt transactionAttempt = new TransactionAttemptTest().getDefaultTransactionAttempt();
    reponse.addTransactionAttempt(transactionAttempt);

    TransactionAttempt transactionAttempt2 = new TransactionAttemptTest().getDefaultTransactionAttempt();
    Calendar calendar = Calendar.getInstance();
    calendar.set(2017, 11, 11, 8, 12, 35);
    calendar.set(Calendar.MILLISECOND, 12);
    transactionAttempt2.setDate(calendar.getTime());
    transactionAttempt2.setResultMessage("My message 2");
    reponse.addTransactionAttempt(transactionAttempt2);

    TransactionAttempt transactionAttempt3 = new TransactionAttemptTest().getDefaultTransactionAttempt();
    calendar.set(2017, 10, 10, 8, 12, 35);
    transactionAttempt3.setDate(calendar.getTime());
    transactionAttempt3.setResultMessage("My message 3");
    reponse.addTransactionAttempt(transactionAttempt3);

    return reponse;
  }
}
