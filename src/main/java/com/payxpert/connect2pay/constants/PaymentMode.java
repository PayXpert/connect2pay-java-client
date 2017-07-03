package com.payxpert.connect2pay.constants;

/**
 * Available Payment Modes to be used to build the payment.
 * 
 * @author jsh
 * 
 */
public enum PaymentMode {
  /**
   * Single payment
   */
  SINGLE("Single"),

  /**
   * Payment will be done on shipping
   */
  ONSHIPPING("OnShipping"),

  /**
   * The payment will be recurrent (subscription)
   */
  RECURRENT("Recurrent"),

  /**
   * Installment payment (or part payment)
   */
  INSTALLMENTS("InstalmentsPayments");

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
        if (mode.equalsIgnoreCase(subType.value)) {
          return subType;
        }
      }
    }
    return null;
  }

  public static PaymentMode valueOfFromName(String name) {
    if (name != null) {
      for (PaymentMode subType : PaymentMode.values()) {
        if (name.equalsIgnoreCase(subType.name())) {
          return subType;
        }
      }
    }
    return null;
  }
}
