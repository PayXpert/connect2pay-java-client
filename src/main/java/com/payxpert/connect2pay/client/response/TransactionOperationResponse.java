package com.payxpert.connect2pay.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payxpert.connect2pay.constants.TransactionOperation;

/**
 * This class represents the response to a Transaction operation API call.
 * 
 */
public abstract class TransactionOperationResponse<T> extends GenericResponse<T> {
  private String code;

  private String message;

  @JsonProperty("transactionID")
  private String transactionId;

  private TransactionOperation operation;

  /**
   * @return the code
   */
  public String getCode() {
    return this.code;
  }

  /**
   * @param code
   *          the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return this.message;
  }

  /**
   * @param message
   *          the message to set
   */
  public void setMessage(String message) {
    this.message = message;
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
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  /**
   * @return The transaction operation
   */
  public TransactionOperation getOperation() {
    return operation;
  }

  /**
   * @param operation
   *          The operation to set
   */
  public void setOperation(TransactionOperation operation) {
    this.operation = operation;
  }
}
