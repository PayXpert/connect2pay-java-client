package com.payxpert.connect2pay.client.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.constants.PaymentStatusValue;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.constants.TransactionOperation;

/**
 * This class represents the response to a payment status request.
 * 
 */
public class PaymentStatusResponse extends GenericResponse<PaymentStatusResponse> {
  // Library internal field, not returned by the API call
  private ResultCode code;
  private String message;

  // Fields returned by the API call
  private String merchantToken;

  private TransactionOperation operation;
  private String errorCode;
  private String errorMessage;

  private PaymentStatusValue status;

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
  public PaymentStatusValue getStatus() {
    return status;
  }

  /**
   * @param status
   *          the status to set
   */
  public void setStatus(PaymentStatusValue status) {
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
    return this.transactions;
  }

  /**
   * 
   * @param index
   *          Index of the element to return (starting at 0)
   * @return The element at the specified position in this list
   */
  public TransactionAttempt getTransactionAttempt(int index) {
    if (this.transactions != null && index >= 0 && index < this.transactions.size()) {
      return this.transactions.get(index);
    }

    return null;
  }

  /**
   * Get a transaction attempt by transactionId
   * 
   * @param transactionId
   *          The transactionId to retrieve
   * @return An optional with the transaction attempt with that id or an empty optional if not found
   */
  public Optional<TransactionAttempt> getTransactionAttempt(String transactionId) {
    if (this.transactions != null && transactionId != null) {
      return this.transactions.stream().filter(t -> transactionId.equals(t.getTransactionId())).findFirst();
    }

    return Optional.empty();
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
    if (this.transactions == null) {
      this.transactions = new ArrayList<>();
    }

    return this.transactions.add(transactionAttempt);
  }

  /**
   * @return The last transaction done
   * @deprecated Use getLastInitialTransactionAttempt()
   */
  @Deprecated
  public TransactionAttempt getLastTransactionAttempt() {
    return this.getLastInitialTransactionAttempt().orElse(null);
  }

  /**
   * Get the last initial transaction attempt. This will return only initial sale, authorize or submission transaction
   * done by the customer. Subsequent transactions of this type with a referral transaction are not considered in that
   * case.
   * 
   * @return An optional with the last initial transaction done or an empty optional if not found
   */
  public Optional<TransactionAttempt> getLastInitialTransactionAttempt() {
    final List<TransactionOperation> includeOps = Arrays.asList(TransactionOperation.AUTHORIZE,
        TransactionOperation.SALE, TransactionOperation.SUBMISSION);

    if (this.transactions != null && this.transactions.size() > 0) {
      return this.transactions.stream().filter(t -> t != null && t.getRefTransactionId() == null
          && t.getOperation() != null && includeOps.contains(t.getOperation())).max(TransactionAttempt::compareTo);
    }

    return Optional.empty();
  }

  /**
   * Get the transaction attempt referring to the provided attempt with the given operation. In case several
   * transactions are found will return the older one.
   * 
   * @return An optional with the transaction found or an empty optional if not found
   */
  public Optional<TransactionAttempt> getReferringTransactionAttempt(TransactionAttempt refTransaction,
      TransactionOperation operation) {
    return getReferringTransactionAttempts(refTransaction, operation).stream().min(TransactionAttempt::compareTo);
  }

  /**
   * Get the transaction attempts referring to the provided attempt with the given operation.
   * 
   * @return A list with the transactions found (sorted by date, older first) or an empty list if not found
   */
  public List<TransactionAttempt> getReferringTransactionAttempts(TransactionAttempt refTransaction,
      TransactionOperation operation) {
    if (refTransaction != null && refTransaction.getTransactionId() != null && operation != null
        && this.transactions != null && this.transactions.size() > 0) {
      return this.transactions.stream().filter(t -> t != null
          && refTransaction.getTransactionId().equals(t.getRefTransactionId()) && operation.equals(t.getOperation()))
          .sorted(TransactionAttempt::compareTo).collect(Collectors.toList());
    }

    return new ArrayList<>();
  }

}