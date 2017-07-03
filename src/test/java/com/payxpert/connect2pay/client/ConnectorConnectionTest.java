package com.payxpert.connect2pay.client;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payxpert.connect2pay.client.requests.TransactionRequestTest;
import com.payxpert.connect2pay.client.response.PaymentResponse;

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
    PaymentResponse response = null;

    long start = System.currentTimeMillis();

    try {
      response = connector.preparePayment(TransactionRequestTest.getDefaultRequest());
      // This must not be executed
      Assert.assertNotNull(null);
    } catch (Exception e) {
      logger.info("connection2InvalidIpAddressFails, catched error : " + e.getMessage());
      Assert.assertTrue(e.getMessage().startsWith("java.net.ConnectException:"));
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
    Connect2payClient connector = new Connect2payClient("https://UnknownHostException.payxpert.com", DEFAULT_ORIGINATOR,
        DEFAULT_PASSWORD);

    connector.setTimeOutInMilliSeconds(TIMEOUT);
    PaymentResponse response = null;
    long duration = 0;

    long start = System.currentTimeMillis();

    try {
      response = connector.preparePayment(TransactionRequestTest.getDefaultRequest());
      // This must not be executed
      fail("Exception not present: An Exception must have been thrown");
    } catch (Exception e) {
      logger.info("connection2UnknowHost, catched error : " + e.getMessage());
      Assert.assertTrue(e.getMessage().startsWith("java.net.UnknownHostException: UnknownHostException.payxpert.com"));
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
    PaymentResponse response = null;
    long duration = 0;
    long start = System.currentTimeMillis();

    try {
      response = connector.preparePayment(TransactionRequestTest.getDefaultRequest());
      // This must not be executed
      Assert.assertNotNull(null);
    } catch (Exception e) {
      String errorMessage = "";
      URL url = null;
      try {
        url = new URL(TEST_URL);
      } catch (MalformedURLException e1) {
        fail(String.format("Cannot resolve the URL: %s. Details: %s", TEST_URL, e.getMessage()));
      }
      errorMessage = String.format("java.net.ConnectException: connection timed out: /%s:%d", url.getHost(),
          url.getDefaultPort());
      if (!errorMessage.equals(e.getMessage())) {
        logger.error(
            "To run this test, the URL " + TEST_URL + " must not be on a directly attached network (or change it).");
      }
      Assert.assertEquals(errorMessage, e.getMessage());
    }

    duration = (System.currentTimeMillis() - start);
    logger.info("connection2HostUnreachable: Execution time was " + duration + " ms.");
    Assert.assertNull(response);
  }
}
