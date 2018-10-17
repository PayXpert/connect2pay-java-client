package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.payxpert.connect2pay.constants.PaymentMethod;

public interface PaymentMethodMixIn {
  @JsonValue
  public String getValue();

  @JsonCreator
  public PaymentMethod fromString(String type);
}
