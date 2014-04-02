package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.payxpert.connect2pay.constants.PaymentType;

public interface PaymentTypeMixIn {
  @JsonValue
  public String getValue();

  @JsonCreator
  public PaymentType fromString(String type);
}
