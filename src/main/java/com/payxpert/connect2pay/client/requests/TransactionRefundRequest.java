package com.payxpert.connect2pay.client.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.sf.oval.constraint.NotNull;

/**
 * Request for the transaction refund API call.
 * 
 * @author jsh
 * 
 */
public class TransactionRefundRequest extends GenericRequest<TransactionRefundRequest> {
  @NotNull
  @JsonIgnore
  private String transactionId;

  @NotNull
  private Integer amount;

  @Override
  protected TransactionRefundRequest getThis() {
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
  public TransactionRefundRequest setTransactionId(String transactionId) {
    this.transactionId = transactionId;
    return getThis();
  }

  public Integer getAmount() {
    return this.amount;
  }

  public TransactionRefundRequest setAmount(Integer amount) {
    this.amount = amount;
    return getThis();
  }
}
