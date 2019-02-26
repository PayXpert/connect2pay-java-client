package com.payxpert.connect2pay.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.payxpert.connect2pay.client.Connect2payClient;
import com.payxpert.connect2pay.client.requests.SubscriptionCancelRequest;
import com.payxpert.connect2pay.client.response.SubscriptionCancelResponse;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.constants.SubscriptionCancelReason;

public class ConnectorSubscriptionCancelTest extends ConnectorTransactionTest {
  @Before
  public void init() {
    this.connector = new Connect2payClient(TEST_URL, DEFAULT_ORIGINATOR, DEFAULT_PASSWORD);
  }

  /**
   * Subscription cancel request with an invalid ID
   * 
   */
  @Test
  public void subscriptionCancelTestInvalidId() {
    SubscriptionCancelResponse cancelResponse = null;
    SubscriptionCancelRequest cancelRequest = new SubscriptionCancelRequest();
    cancelRequest.setSubscriptionId(666999666L);
    cancelRequest.setCancelReason(SubscriptionCancelReason.CHARGEBACK);

    try {
      cancelResponse = connector.cancelSubscription(cancelRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(cancelResponse);
    Assert.assertNotNull(cancelResponse.getCode());
    Assert.assertEquals(ResultCode.INVALID_SUBSCRIPTIONID, cancelResponse.getCode());
  }

  /**
   * Subscription cancel request with a missing reason
   * 
   */
  @Test
  public void subscriptionCancelTestMissingReason() {
    SubscriptionCancelResponse cancelResponse = null;
    SubscriptionCancelRequest cancelRequest = new SubscriptionCancelRequest();
    cancelRequest.setSubscriptionId(666999666L);

    try {
      cancelResponse = connector.cancelSubscription(cancelRequest);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Assert.assertNotNull(cancelResponse);
    Assert.assertNotNull(cancelResponse.getCode());
    Assert.assertEquals(ResultCode.DATA_MISSING_PARAMETER, cancelResponse.getCode());
  }
}
