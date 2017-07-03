package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.payxpert.connect2pay.constants.PaymentStatusValue;

public interface TransactionStatusValueMixIn {
  @JsonValue
  public String getLabel();

  @JsonCreator
  public PaymentStatusValue fromString(String operation);
}
