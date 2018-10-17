package com.payxpert.connect2pay.constants;

/**
 * Available Payment Types to be used to build a new payment.
 * 
 * @author jsh
 * 
 */
public enum PaymentMethod {
  BANK_TRANSFER("BankTransfer", false), /* */
  CREDIT_CARD("CreditCard", false), /* */
  DIRECT_DEBIT("DirectDebit", true), /* */
  TODITO_CASH("ToditoCash", false), /* */
  WECHAT("WeChat", false), /* */
  LINE("Line", false), /* */
  ALIPAY("AliPay", false), /* */
  CHARGEBACK("Chargeback", false), /* */
  REVERSAL("Reversal", false);

  private String value;
  private Boolean async;

  PaymentMethod(String value, Boolean async) {
    this.value = value;
    this.async = async;
  }

  public String getValue() {
    return this.value;
  }

  public Boolean isAsync() {
    return this.async;
  }

  public String valueToString() {
    return this.value.toLowerCase();
  }

  public PaymentMethod fromString(String type) {
    return valueOfFromString(type);
  }

  public static PaymentMethod valueOfFromString(String type) {
    if (type != null) {
      for (PaymentMethod subType : PaymentMethod.values()) {
        if (type.equalsIgnoreCase(subType.value)) {
          return subType;
        }
      }
    }
    return null;
  }

  public static PaymentMethod valueOfFromName(String name) {
    if (name != null) {
      for (PaymentMethod subType : PaymentMethod.values()) {
        if (name.equalsIgnoreCase(subType.name())) {
          return subType;
        }
      }
    }
    return null;
  }
}
