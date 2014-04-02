package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.annotation.JsonValue;

public interface SubscriptionCancelReasonMixIn {
  @JsonValue
  public Integer getCode();
}
