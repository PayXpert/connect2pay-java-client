package com.payxpert.connect2pay.client.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.sf.oval.constraint.NotNull;

/**
 * Request for the transaction refund API call.
 * 
 * @author payxpert
 * 
 */
public class TransactionRefundConfirmRequest extends GenericRequest<TransactionRefundConfirmRequest> {
  @NotNull
  @JsonIgnore
  private String transactionId;

  @Override
  protected TransactionRefundConfirmRequest getThis() {
    return this;
  }

  /**
   * @return the transactionId
   */
  public String getTransactionId() {
    return this.transactionId;
  }

  /**
   * @param transactionId
   *          the transactionId to set
   */
  public TransactionRefundConfirmRequest setTransactionId(String transactionId) {
    this.transactionId = transactionId;
    return getThis();
  }
}
