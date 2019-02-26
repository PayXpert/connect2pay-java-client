package com.payxpert.connect2pay.client.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.client.containers.TransactionAttemptTest;
import com.payxpert.connect2pay.constants.ResultCode;

public class TransactionInfoResponseTest {
  @Test
  public void testTransactionInfoResponse() {
    TransactionInfoResponse response = getDefaultResponse();

    assertEquals(ResultCode.SUCCESS, response.getCode());

    TransactionAttempt transactionAttempt = response.getTransactionInfo();
    assertNotNull(transactionAttempt);
    assertEquals("000", transactionAttempt.getResultCode());
    assertEquals("Success", transactionAttempt.getResultMessage());
  }

  public static TransactionInfoResponse getDefaultResponse() {
    TransactionInfoResponse response = new TransactionInfoResponse();
    response.setCode(ResultCode.SUCCESS);

    TransactionAttempt transactionAttempt = new TransactionAttemptTest().getDefaultTransactionAttempt();
    response.setTransactionInfo(transactionAttempt);

    return response;
  }
}
