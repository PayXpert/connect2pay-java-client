package com.payxpert.connect2pay.constants;

/**
 * Global result codes of an API call. The Connect2payClient will also interpret some raw HTTP response codes and
 * translate them to ResultCode to ease the implementation.
 * 
 * @author jsh
 * 
 */
public enum ResultCode {
  SUCCESS(200), /* */
  TRANSACTION_NOT_FOUND(312), /* */
  REQUEST_MISSING_PARAMETER(400), /* */
  DATA_MISSING_PARAMETER(401), /* */
  JSON_ERROR(402), /* */
  AUTH_FAILED(403), /* */
  NOT_FOUND(404), /* */
  INTERNAL_OPERATION_ERROR(500), /* */
  TRANSACTION_INCORRECT_DATA(501), /* */
  SUBSCRIPTION_ALREADY_EXPIRED(674), /* */
  INVALID_SUBSCRIPTIONID(678);

  private Integer code;

  ResultCode(Integer code) {
    this.code = code;
  }

  public Integer getCode() {
    return this.code;
  }

  public ResultCode fromCode(Integer code) {
    return valueOfFromCode(code);
  }

  public static ResultCode valueOfFromCode(Integer code) {
    if (code != null) {
      for (ResultCode subType : ResultCode.values()) {
        if (code.equals(subType.code)) {
          return subType;
        }
      }
    }
    return null;
  }

  public static ResultCode valueOfFromName(String name) {
    if (name != null) {
      for (ResultCode subType : ResultCode.values()) {
        if (name.equalsIgnoreCase(subType.name())) {
          return subType;
        }
      }
    }
    return null;
  }
}
