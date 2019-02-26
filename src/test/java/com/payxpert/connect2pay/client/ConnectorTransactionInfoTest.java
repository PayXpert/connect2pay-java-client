package com.payxpert.connect2pay.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.requests.TransactionInfoRequest;
import com.payxpert.connect2pay.client.response.TransactionInfoResponse;
import com.payxpert.connect2pay.constants.ResultCode;

public class ConnectorTransactionInfoTest extends ConnectorTransactionTest {
  @Before
  public void init() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
  }

  /**
   * Transaction info request with an invalid transaction Id
   * 
   */
  @Test
  public void transactionInfoTestInvalidId() {
    TransactionInfoResponse infoResponse = null;
    TransactionInfoRequest infoRequest = new TransactionInfoRequest();
    infoRequest.setTransactionId("666999666");

    try {
      infoResponse = connector.getTransactionInfo(infoRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertNotNull(infoResponse);
    assertNotNull(infoResponse.getCode());
    assertEquals(ResultCode.NOT_FOUND, infoResponse.getCode());
  }
}
