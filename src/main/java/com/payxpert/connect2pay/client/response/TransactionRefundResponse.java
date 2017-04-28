package com.payxpert.connect2pay.client.response;

import com.payxpert.connect2pay.constants.ResultCode;

/**
 * This class represents the response to the Transaction Refund API call.
 * 
 * @author jsh <jsh@payxpert.com>
 * @version 2.0
 * 
 */
public class TransactionRefundResponse extends GenericResponse<TransactionRefundResponse> {
  private ResultCode code;
  private String message;
  private String transactionId;

  /**
   * @return the code
   */
  public ResultCode getCode() {
    return code;
  }

  /**
   * @param code
   *          the code to set
   */
  public void setCode(ResultCode code) {
    this.code = code;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
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
    return transactionId;
  }

  /**
   * @param transactionId
   *          the transactionId to set
   */
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
}
