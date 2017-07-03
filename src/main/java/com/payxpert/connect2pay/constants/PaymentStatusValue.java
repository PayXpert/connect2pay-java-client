package com.payxpert.connect2pay.constants;

/**
 * Values for payment status.
 * 
 * @author jsh
 * 
 */
public enum PaymentStatusValue {
  AUTHORIZED("Authorized"), NOT_AUTHORIZED("Not authorized"), EXPIRED("Expired"), CALL_FAILED(
      "Call failed"), NOT_PROCESSED("Not processed"), PENDING("Pending"), UNDEFINED("");

  private String label;

  PaymentStatusValue(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public PaymentStatusValue fromString(String label) {
    return valueOfFromLabel(label);
  }

  public static PaymentStatusValue valueOfFromLabel(String label) {
    if (label != null) {
      for (PaymentStatusValue status : PaymentStatusValue.values()) {
        if (status.label.equalsIgnoreCase(label)) {
          return status;
        }
      }
    }
    return UNDEFINED;
  }

  public static PaymentStatusValue valueOfFromName(String name) {
    if (name != null) {
      for (PaymentStatusValue subType : PaymentStatusValue.values()) {
        if (name.equalsIgnoreCase(subType.name())) {
          return subType;
        }
      }
    }
    return UNDEFINED;
  }
}
