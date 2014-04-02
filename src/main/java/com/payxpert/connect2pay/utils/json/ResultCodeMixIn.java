package com.payxpert.connect2pay.utils.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.payxpert.connect2pay.constants.ResultCode;

public interface ResultCodeMixIn {
  @JsonValue
  public String getCode();

  @JsonCreator
  public ResultCode fromCode(Integer codeValue);
}
