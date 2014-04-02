package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.payxpert.connect2pay.constants.TransactionStatusValue;

public interface TransactionStatusValueMixIn {
  @JsonValue
  public String getLabel();

  @JsonCreator
  public TransactionStatusValue fromString(String operation);
}
