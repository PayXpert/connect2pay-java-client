package com.payxpert.connect2pay.client.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.sf.oval.constraint.NotNull;

public class TransactionCaptureRequest extends GenericRequest<TransactionCaptureRequest> {

  @NotNull
  @JsonIgnore
  private String transactionId;

  @NotNull
  private Integer amount;

  @Override
  protected TransactionCaptureRequest getThis() {
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
  public TransactionCaptureRequest setTransactionId(String transactionId) {
    this.transactionId = transactionId;
    return getThis();
  }

  public Integer getAmount() {
    return this.amount;
  }

  public TransactionCaptureRequest setAmount(Integer amount) {
    this.amount = amount;
    return getThis();
  }
}
