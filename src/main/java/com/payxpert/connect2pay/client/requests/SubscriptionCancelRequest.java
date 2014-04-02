package com.payxpert.connect2pay.client.requests;

import net.sf.oval.constraint.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payxpert.connect2pay.constants.SubscriptionCancelReason;

/**
 * Request for the subscription cancel API call.
 * 
 * @author jsh
 * 
 */
public class SubscriptionCancelRequest extends GenericRequest<SubscriptionCancelRequest> {
  @NotNull
  @JsonIgnore
  private Long subscriptionId;

  @NotNull
  private SubscriptionCancelReason cancelReason;

  @Override
  protected SubscriptionCancelRequest getThis() {
    return this;
  }

  /**
   * @return the subscriptionId
   */
  public Long getSubscriptionId() {
    return subscriptionId;
  }

  /**
   * @param subscriptionId
   *          the subscriptionId to set
   */
  public SubscriptionCancelRequest setSubscriptionId(Long subscriptionId) {
    this.subscriptionId = subscriptionId;
    return getThis();
  }

  public SubscriptionCancelReason getCancelReason() {
    return cancelReason;
  }

  public SubscriptionCancelRequest setCancelReason(SubscriptionCancelReason cancelReason) {
    this.cancelReason = cancelReason;
    return getThis();
  }
}
