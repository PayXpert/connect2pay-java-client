package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.payxpert.connect2pay.constants.TransactionOperation;

public interface TransactionOperationMixIn {
  @JsonValue
  public String valueToString();

  @JsonCreator
  public TransactionOperation fromString(String operation);
}
