package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.payxpert.connect2pay.constants.PaymentMode;

public interface PaymentModeMixIn {
  @JsonValue
  public String getValue();

  @JsonCreator
  public PaymentMode fromString(String type);
}
