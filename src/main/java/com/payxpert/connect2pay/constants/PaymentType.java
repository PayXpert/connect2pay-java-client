package com.payxpert.connect2pay.constants;

/**
 * Available Payment Types to be used to build a new transaction.
 * 
 * @author jsh
 * 
 */
public enum PaymentType {
  CREDIT_CARD("CreditCard"), DIRECT_DEBIT("DirectDebit"), TODITO_CASH("ToditoCash");

  private String value;

  PaymentType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public String valueToString() {
    return this.value.toLowerCase();
  }

  public PaymentType fromString(String type) {
    return valueOfFromString(type);
  }

  public static PaymentType valueOfFromString(String type) {
    if (type != null) {
      for (PaymentType subType : PaymentType.values()) {
        if (type.toLowerCase().equals(subType.value.toLowerCase())) {
          return subType;
        }
      }
    }
    return null;
  }

  public static PaymentType valueOfFromName(String name) {
    if (name != null) {
      for (PaymentType subType : PaymentType.values()) {
        if (name.toLowerCase().equals(subType.name().toLowerCase())) {
          return subType;
        }
      }
    }
    return null;
  }
}
