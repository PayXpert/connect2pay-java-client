package com.payxpert.connect2pay.constants;

/**
 * Values for transaction status.
 * 
 * @author jsh
 * 
 */
public enum TransactionStatusValue {
  AUTHORIZED("Authorized"), NOT_AUTHORIZED("Not authorized"), EXPIRED("Expired"), CALL_FAILED("Call failed"), NOT_PROCESSED(
      "Not processed"), UNDEFINED("");

  private String label;

  TransactionStatusValue(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public TransactionStatusValue fromString(String label) {
    return valueOfFromLabel(label);
  }

  public static TransactionStatusValue valueOfFromLabel(String label) {
    if (label != null) {
      for (TransactionStatusValue status : TransactionStatusValue.values()) {
        if (status.label.toLowerCase().equals(label.toLowerCase())) {
          return status;
        }
      }
    }
    return UNDEFINED;
  }

  public static TransactionStatusValue valueOfFromName(String name) {
    if (name != null) {
      for (TransactionStatusValue subType : TransactionStatusValue.values()) {
        if (name.toLowerCase().equals(subType.name().toLowerCase())) {
          return subType;
        }
      }
    }
    return UNDEFINED;
  }
}
