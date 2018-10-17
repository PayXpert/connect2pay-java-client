package com.payxpert.connect2pay.client.response;

import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.constants.ResultCode;

/**
 * This class represents the response to a transaction info request.
 * 
 */
public class TransactionInfoResponse extends GenericResponse<TransactionInfoResponse> {
  // Library internal field, not returned by the API call
  private ResultCode code;
  private String message;

  private TransactionAttempt transactionInfo;

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

  public TransactionAttempt getTransactionInfo() {
    return this.transactionInfo;
  }

  public void setTransactionInfo(TransactionAttempt transactionInfo) {
    this.transactionInfo = transactionInfo;
  }
}