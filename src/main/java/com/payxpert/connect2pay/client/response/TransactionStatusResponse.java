package com.payxpert.connect2pay.client.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.constants.TransactionOperation;
import com.payxpert.connect2pay.constants.TransactionStatusValue;

/**
 * This class represents the response to a transaction status request.
 * 
 * @author jsh
 * 
 */
public class TransactionStatusResponse extends GenericResponse<TransactionStatusResponse> {
  // Library internal field, not returned by the API call
  private ResultCode code;
  private String message;

  // Fields returned by the API call
  private String merchantToken;

  private TransactionOperation operation;
  private String errorCode;
  private String errorMessage;

  private TransactionStatusValue status;

  private String ctrlCustomData;
  @JsonProperty("orderID")
  private String orderId;
  private String currency;
  private Integer amount;

  private List<TransactionAttempt> transactions;

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
   * @return the merchantToken
   */
  public String getMerchantToken() {
    return merchantToken;
  }

  /**
   * @param merchantToken
   *          the merchantToken to set
   */
  public void setMerchantToken(String merchantToken) {
    this.merchantToken = merchantToken;
  }

  /**
   * @return the operation
   */
  public TransactionOperation getOperation() {
    return operation;
  }

  /**
   * @param operation
   *          the operation to set
   */
  public void setOperation(TransactionOperation operation) {
    this.operation = operation;
  }

  /**
   * @return the errorCode
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * @param errorCode
   *          the errorCode to set
   */
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * @return the errorMessage
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * @param errorMessage
   *          the errorMessage to set
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * @return the status
   */
  public TransactionStatusValue getStatus() {
    return status;
  }

  /**
   * @param status
   *          the status to set
   */
  public void setStatus(TransactionStatusValue status) {
    this.status = status;
  }

  /**
   * @return the ctrlCustomData
   */
  public String getCtrlCustomData() {
    return ctrlCustomData;
  }

  /**
   * @param ctrlCustomData
   *          the ctrlCustomData to set
   */
  public void setCtrlCustomData(String ctrlCustomData) {
    this.ctrlCustomData = ctrlCustomData;
  }

  /**
   * @return the orderId
   */
  public String getOrderId() {
    return orderId;
  }

  /**
   * @param orderId
   *          the orderId to set
   */
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  /**
   * @return the currency
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * @param currency
   *          the currency to set
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * @return the amount
   */
  public Integer getAmount() {
    return amount;
  }

  /**
   * @param amount
   *          the amount to set
   */
  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public List<TransactionAttempt> getTransactions() {
    return transactions;
  }

  /**
   * 
   * @param index
   *          Index of the element to return
   * @return The element at the specified position in this list
   */
  public TransactionAttempt getTransactionAttempt(int index) {
    if (transactions != null && index >= 0 && index < transactions.size()) {
      return transactions.get(index);
    }
    return null;
  }

  public void setTransactions(List<TransactionAttempt> transactions) {
    this.transactions = transactions;
  }

  /**
   * 
   * @param transactionAttempt
   *          element to add
   * @return true
   */
  public boolean addTransactionAttempt(TransactionAttempt transactionAttempt) {
    if (transactions == null) {
      this.transactions = new ArrayList<>();
    }
    return transactions.add(transactionAttempt);
  }

  /**
   * 
   * @return The last transaction done
   */
  public TransactionAttempt getLastTransactionAttempt() {
    if (this.transactions != null && this.transactions.size() > 0) {
      return this.transactions.stream().max(TransactionAttempt::compareTo).orElse(null);
    }
    return null;
  }
}