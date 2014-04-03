package com.payxpert.connect2pay.constants;

/**
 * Types of operation done by a transaction. This is enforced on the payment
 * page application and returned in the transaction status.
 * 
 * @author jsh
 * 
 */
public enum TransactionOperation {
  /* Payment authorization */
  AUTHORIZE,
  /* Payment (authorize + capture) */
  SALE;

  public String valueToString() {
    return this.name().toLowerCase();
  }

  public TransactionOperation fromString(String operation) {
    return valueOfFromString(operation);
  }

  public static TransactionOperation valueOfFromString(String operation) {
    if (operation != null) {
      for (TransactionOperation op : TransactionOperation.values()) {
        if (op.name().equalsIgnoreCase(operation)) {
          return op;
        }
      }
    }
    return null;
  }
}
