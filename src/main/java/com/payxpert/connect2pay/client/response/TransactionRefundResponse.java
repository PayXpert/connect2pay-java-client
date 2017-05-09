package com.payxpert.connect2pay.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents the response to the Transaction Refund API call.
 * 
 * @author jsh <jsh@payxpert.com>
 * @version 2.0
 * 
 */
public class TransactionRefundResponse extends GenericResponse<TransactionRefundResponse> {
  private String code;

  private String message;

  @JsonProperty("transactionID")
  private String transactionId;

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
}
