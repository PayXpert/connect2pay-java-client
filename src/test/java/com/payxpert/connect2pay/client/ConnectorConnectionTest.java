package com.payxpert.connect2pay.client;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payxpert.connect2pay.client.Connect2payClient;
import com.payxpert.connect2pay.client.requests.TransactionRequestTest;
import com.payxpert.connect2pay.client.response.TransactionResponse;

public class ConnectorConnectionTest extends ConnectorTransactionTest {
  private static final String TEST_URL = "https://10.1.222.222";
  private static final Integer TIMEOUT = 5000;

  private static final Logger logger = LoggerFactory.getLogger(ConnectorConnectionTest.class);

  /**
   * Test the connection fails to invalid IP address
   */
  @Test
  public void connection2InvalidIpAddressFails() {
    Connect2payClient connector = new Connect2payClient("https://127.0.0.255", DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
    long duration = 0;
    connector.setTimeOutInMilliSeconds(TIMEOUT);
    TransactionResponse response = null;

    long start = System.currentTimeMillis();

    try {
      response = connector.prepareTransaction(TransactionRequestTest.getDefaultRequest());
      // This must not be executed
      Assert.assertNotNull(null);
    } catch (Exception e) {
      logger.info("connection2InvalidIpAddressFails, catched error : " + e.getMessage());
      Assert.assertTrue(e.getMessage().startsWith("java.net.ConnectException: Connection refused"));
    }

    duration = (System.currentTimeMillis() - start);
    logger.info("connection2InvalidIpAddressFails : Execution time was " + duration + " ms.");
    Assert.assertNull(response);
  }

  /**
   * Test the connection fails to a unknown host
   */
  @Test
  public void connection2UnknowHost() {
    Connect2payClient connector = new Connect2payClient("https://UnknownHostException.payxpert.com",
        DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);

    connector.setTimeOutInMilliSeconds(TIMEOUT);
    TransactionResponse response = null;
    long duration = 0;

    long start = System.currentTimeMillis();

    try {
      response = connector.prepareTransaction(TransactionRequestTest.getDefaultRequest());
      // This must not be executed
      Assert.assertNotNull(null);
    } catch (Exception e) {
      logger.info("connection2UnknowHost, catched error : " + e.getMessage());
      Assert.assertTrue(e.getMessage().startsWith(
          "java.net.ConnectException: https://UnknownHostException.payxpert.com"));
    }

    duration = (System.currentTimeMillis() - start);
    logger.info("connection2UnknowHost: Execution time was " + duration + " ms.");
    Assert.assertNull(response);
  }

  /**
   * Test the connection fails to a host unreachable
   */
  @Test
  public void connection2HostUnreachable() {
    Connect2payClient connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);

    connector.setTimeOutInMilliSeconds(TIMEOUT);
    TransactionResponse response = null;
    long duration = 0;
    long start = System.currentTimeMillis();

    try {
      response = connector.prepareTransaction(TransactionRequestTest.getDefaultRequest());
      // This must not be executed
      Assert.assertNotNull(null);
    } catch (Exception e) {
      String errorMessage = "";
      errorMessage = String.format("java.util.concurrent.TimeoutException: No response received after %d", TIMEOUT);

      if (!errorMessage.equals(e.getMessage())) {
        logger.error("To run this test, the URL " + TEST_URL
            + " must not be on a directly attached network (or change it).");
      }
      Assert.assertEquals(errorMessage, e.getMessage());
    }

    duration = (System.currentTimeMillis() - start);
    logger.info("connection2HostUnreachable: Execution time was " + duration + " ms.");
    Assert.assertNull(response);
  }
}
