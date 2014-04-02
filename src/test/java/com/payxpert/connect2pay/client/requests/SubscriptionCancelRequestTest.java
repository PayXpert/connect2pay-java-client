package com.payxpert.connect2pay.client.requests;

import org.junit.Test;

import com.payxpert.connect2pay.constants.SubscriptionCancelReason;
import com.payxpert.connect2pay.exception.BadRequestException;

public class SubscriptionCancelRequestTest {
  @Test
  public void testSubscriptionRequestWellformed() throws Exception {
    SubscriptionCancelRequest request = getDefaultRequest();

    // No exception must be thrown
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testSubscriptionRequestMissingSubscriptionId() throws Exception {
    SubscriptionCancelRequest request = getDefaultRequest();
    request.setSubscriptionId(null);
    request.validate();
  }

  @Test(expected = BadRequestException.class)
  public void testSubscriptionRequestMissingReason() throws Exception {
    SubscriptionCancelRequest request = getDefaultRequest();
    request.setCancelReason(null);
    request.validate();
  }

  public static SubscriptionCancelRequest getDefaultRequest() {
    SubscriptionCancelRequest request = new SubscriptionCancelRequest();

    request.setSubscriptionId(123456L).setCancelReason(SubscriptionCancelReason.CHARGEBACK);

    return request;
  }
}
