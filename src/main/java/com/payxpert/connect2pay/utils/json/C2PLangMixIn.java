package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.payxpert.connect2pay.constants.C2PLang;

public interface C2PLangMixIn {
  @JsonValue
  public String getValue();

  @JsonCreator
  public C2PLang fromString(String type);
}
