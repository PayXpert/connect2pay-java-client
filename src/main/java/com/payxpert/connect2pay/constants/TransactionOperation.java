package com.payxpert.connect2pay.constants;

/**
 * Types of operation done by a transaction. This is enforced on the payment page application and returned in the
 * payment status.
 * 
 */
public enum TransactionOperation {
  /* Payment authorization */
  AUTHORIZE,
  /* Pre-Auth capture */
  CAPTURE,
  /* Payment (authorize + capture) */
  SALE,
  /* Initial operation for asynchronous payment type */
  SUBMISSION,
  /* Confirmation operation for asynchronous payment type */
  COLLECTION,
  /* Transaction cancel */
  CANCEL,
  /* Transaction refund */
  REFUND,
  /* Transaction refund request */
  REFUND_REQUEST,
  /* Transaction rebill */
  REBILL,
  /* Contestation operation */
  CHARGEBACK,
  /* Dispute by the customer */
  DISPUTE,
  /* Request for information from the bank */
  RETRIEVAL,
  /* Reversal operation (internal refund by the bank) */
  REVERSAL,
  /* Fraud declaration (no fund movement) */
  FRAUD,
  /* Chargeback cancellation by the bank */
  REPRESENTMENT;

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
