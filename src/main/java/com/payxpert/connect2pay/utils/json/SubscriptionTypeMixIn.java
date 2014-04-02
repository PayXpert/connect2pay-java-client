package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.payxpert.connect2pay.constants.SubscriptionType;

public interface SubscriptionTypeMixIn {
  @JsonValue
  public String getValue();

  @JsonCreator
  public SubscriptionType fromString(String type);
}
