package com.payxpert.connect2pay.client.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.sf.oval.constraint.NotNull;

/**
 * Request for the transaction cancel API call.
 * 
 */
public class TransactionCancelRequest extends GenericRequest<TransactionCancelRequest> {
  @NotNull
  @JsonIgnore
  private String transactionId;

  @NotNull
  private Integer amount;

  @Override
  protected TransactionCancelRequest getThis() {
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
  public TransactionCancelRequest setTransactionId(String transactionId) {
    this.transactionId = transactionId;
    return getThis();
  }

  public Integer getAmount() {
    return this.amount;
  }

  public TransactionCancelRequest setAmount(Integer amount) {
    this.amount = amount;
    return getThis();
  }
}
