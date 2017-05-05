package com.payxpert.connect2pay.client.containers;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.payxpert.connect2pay.constants.PaymentType;
import com.payxpert.connect2pay.constants.TransactionOperation;
import com.payxpert.connect2pay.constants.TransactionStatusValue;
import com.payxpert.connect2pay.utils.Utils;
import com.payxpert.connect2pay.utils.json.PaymentMeanInfoDeserializer;

/**
 * This represents an attempt to process a payment. It varies according to the payment type.
 * 
 * @author Alexandre Ch
 * 
 */

public class TransactionAttempt {

  protected static final Logger logger = LoggerFactory.getLogger(TransactionAttempt.class);

  private Date date;

  private PaymentType paymentType;

  @JsonDeserialize(using = PaymentMeanInfoDeserializer.class)
  private String paymentMeanInfo;

  private TransactionOperation operation;
  private String resultCode;
  private String resultMessage;

  private TransactionStatusValue status;
  @JsonProperty("transactionID")
  private String transactionId;
  @JsonProperty("subscriptionID")
  private Long subscriptionId;

  /**
   * Shopper informations
   * 
   */
  private Shopper shopper;

  // Credit Card Transaction specific information
  private String statementDescriptor;

  /**
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date
   *          the date to set
   */
  public void getDate(Date date) {
    this.date = date;
  }

  /**
   * @return the paymentType
   */
  public PaymentType getPaymentType() {
    return paymentType;
  }

  /**
   * @param paymentType
   *          the paymentType to set
   */
  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  /**
   * Return the payment mean info for Credit Card transaction. This is a shortcut for
   * getPaymentMeanInfo(CreditCardPaymentMeanInfo.class) (with paymentType check).
   * 
   * @return The CreditCardPaymentMeanInfo for this transaction
   */
  public CreditCardPaymentMeanInfo getCCPaymentMeanInfo() {
    if (!PaymentType.CREDIT_CARD.equals(this.paymentType)) {
      logger.error("Payment type is not Credit Card, can not return payment mean info for credit card.");
      return null;
    }
    return this.getPaymentMeanInfo(CreditCardPaymentMeanInfo.class);
  }

  /**
   * Return the payment mean info for Bank transfer transaction. This is a shortcut for
   * getPaymentMeanInfo(BankTransferPaymentMeanInfo.class) (with paymentType check).
   * 
   * @return The BankTransferPaymentMeanInfo for this transaction
   */
  public BankTransferPaymentMeanInfo getBTPaymentMeanInfo() {
    if (!PaymentType.BANK_TRANSFER.equals(this.paymentType)) {
      logger.error("Payment type is not Bank Transfer, can not return payment mean info for bank transfer.");
      return null;
    }
    return this.getPaymentMeanInfo(BankTransferPaymentMeanInfo.class);
  }

  /**
   * Return the payment mean info for Todito transaction. This is a shortcut for
   * getPaymentMeanInfo(ToditoPaymentMeanInfo.class) (with paymentType check).
   * 
   * @return The ToditoPaymentMeanInfo for this transaction
   */
  public ToditoPaymentMeanInfo getToditoPaymentMeanInfo() {
    if (!PaymentType.TODITO_CASH.equals(this.paymentType)) {
      logger.error("Payment type is not Todito cash, can not return payment mean info for Todito cash.");
      return null;
    }
    return this.getPaymentMeanInfo(ToditoPaymentMeanInfo.class);
  }

  /**
   * @param c
   *          Class of the PaymentMeanInfo to return, this varies according to the paymentType value
   * @return the paymentMeanInfo
   */
  public <T extends PaymentMeanInfo> T getPaymentMeanInfo(Class<T> c) {
    T pmInfo = null;
    if (this.paymentMeanInfo != null) {
      ObjectMapper mapper = Utils.getJSONObjectMapper();
      try {
        pmInfo = mapper.readValue(this.paymentMeanInfo, c);
      } catch (JsonParseException e) {
        logger.error("Error parsing payment mean information: " + e.getMessage());
      } catch (JsonMappingException e) {
        logger.error("Error mapping payment mean information: " + e.getMessage());
      } catch (IOException e) {
        logger.error("IO Error when deserializing payment mean information: " + e.getMessage());
      }
    }

    return pmInfo;
  }

  /**
   * @param paymentMeanInfo
   *          the paymentMeanInfo to set
   */
  public void setPaymentMeanInfo(String paymentMeanInfo) {
    this.paymentMeanInfo = paymentMeanInfo;
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
   * @return the resultCode
   */
  public String getResultCode() {
    return resultCode;
  }

  /**
   * @param resultCode
   *          the resultCode to set
   */
  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  /**
   * @return the resultMessage
   */
  public String getResultMessage() {
    return resultMessage;
  }

  /**
   * @param resultMessage
   *          the resultMessage to set
   */
  public void setResultMessage(String resultMessage) {
    this.resultMessage = resultMessage;
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

  /**
   * @return the subscriptionId
   */
  public Long getSubscriptionId() {
    return subscriptionId;
  }

  /**
   * @param subscriptionId
   *          the subscriptionId to set
   */
  public void setSubscriptionId(Long subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  /**
   * @return the shopper
   */
  public Shopper getShopper() {
    return this.shopper;
  }

  /**
   * @param shopper
   *          the shopper to set
   */
  public void setShopper(Shopper shopper) {
    this.shopper = shopper;
  }

  /**
   * @return the statementDescriptor
   */
  public String getStatementDescriptor() {
    return statementDescriptor;
  }

  /**
   * @param statementDescriptor
   *          the statementDescriptor to set
   */
  public void setStatementDescriptor(String statementDescriptor) {
    this.statementDescriptor = statementDescriptor;
  }

  /**
   * @return the cardHolderName
   * @deprecated cardHolderName has been moved into paymentMeanInfo.cardHolderName
   */
  @Deprecated
  public String getCardHolderName() {
    if (this.paymentMeanInfo != null && PaymentType.CREDIT_CARD.equals(this.paymentType)) {
      CreditCardPaymentMeanInfo pmInfo = this.getPaymentMeanInfo(CreditCardPaymentMeanInfo.class);
      if (pmInfo != null) {
        return pmInfo.getCardHolderName();
      }
    }

    return null;
  }

  /**
   * @param cardHolderName
   *          the cardHolderName to set
   * @deprecated cardHolderName has been moved into paymentMeanInfo.cardHolderName and cannot be set any more
   */
  @Deprecated
  public void setCardHolderName(String cardHolderName) {
  }

}
