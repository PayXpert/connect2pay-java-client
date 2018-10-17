package com.payxpert.connect2pay.client.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.sf.oval.constraint.NotNull;

/**
 * Request for the transaction information API call.
 * 
 */
public class TransactionInfoRequest extends GenericRequest<TransactionInfoRequest> {
  @NotNull
  @JsonIgnore
  private String transactionId;

  @Override
  protected TransactionInfoRequest getThis() {
    return this;
  }

  /**
   * @return The transactionId
   */
  public String getTransactionId() {
    return this.transactionId;
  }

  /**
   * @param transactionId
   *          the transactionId to set
   * 
   * @return The current request for method chaining
   */
  public TransactionInfoRequest setTransactionId(String transactionId) {
    this.transactionId = transactionId;
    return getThis();
  }
}
