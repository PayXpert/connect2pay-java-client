package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.payxpert.connect2pay.constants.ShippingType;

public interface ShippingTypeMixIn {
  @JsonValue
  public String getValue();

  @JsonCreator
  public ShippingType fromString(String type);
}
