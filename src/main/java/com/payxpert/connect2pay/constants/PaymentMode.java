package com.payxpert.connect2pay.constants;

/**
 * Available Payment Modes to be used to build the transaction.
 * 
 * @author jsh
 * 
 */
public enum PaymentMode {
  SINGLE("Single"), ONSHIPPING("OnShipping"), RECURRENT("Recurrent"), INSTALMENTS("InstalmentsPayments");

  private String value;

  PaymentMode(String value) {
    this.value = value;
  }

  /**
   * Get the value to use in API calls
   * 
   * @return The value of the current enum member
   */
  public String getValue() {
    return this.value;
  }

  public String valueToString() {
    return this.value.toLowerCase();
  }

  public PaymentMode fromString(String type) {
    return valueOfFromString(type);
  }

  public static PaymentMode valueOfFromString(String mode) {
    if (mode != null) {
      for (PaymentMode subType : PaymentMode.values()) {
        if (mode.toLowerCase().equals(subType.value.toLowerCase())) {
          return subType;
        }
      }
    }
    return null;
  }

  public static PaymentMode valueOfFromName(String name) {
    if (name != null) {
      for (PaymentMode subType : PaymentMode.values()) {
        if (name.toLowerCase().equals(subType.name().toLowerCase())) {
          return subType;
        }
      }
    }
    return null;
  }
}
