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
import com.payxpert.connect2pay.constants.PaymentMethod;
import com.payxpert.connect2pay.constants.PaymentStatusValue;
import com.payxpert.connect2pay.constants.TransactionOperation;
import com.payxpert.connect2pay.utils.Utils;
import com.payxpert.connect2pay.utils.json.PaymentMeanInfoDeserializer;

/**
 * This represents an attempt to process a payment. It varies according to the payment method.
 */

public class TransactionAttempt implements Comparable<TransactionAttempt> {
  protected static final Logger logger = LoggerFactory.getLogger(TransactionAttempt.class);

  private Date date;

  private Integer amount;

  private PaymentMethod paymentMethod;

  private String paymentNetwork;

  @JsonDeserialize(using = PaymentMeanInfoDeserializer.class)
  private String paymentMeanInfo;

  private TransactionOperation operation;

  private String resultCode;

  private String resultMessage;

  private PaymentStatusValue status;

  @JsonProperty("transactionID")
  private String transactionId;

  @JsonProperty("refTransactionID")
  private String refTransactionId;

  @JsonProperty("providerTransactionID")
  private String providerTransactionId;

  @JsonProperty("subscriptionID")
  private Long subscriptionId;

  /**
   * Shopper informations
   * 
   */
  private Shopper shopper;

  /**
   * @return the date
   */
  public Date getDate() {
    return this.date;
  }

  /**
   * @param date
   *          the date to set
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @return the amount
   */
  public Integer getAmount() {
    return this.amount;
  }

  /**
   * @param amount
   *          the amount to set
   */
  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  @Deprecated
  public PaymentMethod getPaymentType() {
    return this.getPaymentMethod();
  }

  @Deprecated
  public void setPaymentType(PaymentMethod paymentType) {
    this.setPaymentMethod(paymentType);
  }

  /**
   * @return the paymentMethod
   */
  public PaymentMethod getPaymentMethod() {
    return this.paymentMethod;
  }

  /**
   * @param paymentMethod
   *          the paymentMethod to set
   */
  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  /**
   * @return the payment network
   */
  public String getPaymentNetwork() {
    return this.paymentNetwork;
  }

  /**
   * @param paymentNetwork
   *          The payment network to set
   */
  public void setPaymentNetwork(String paymentNetwork) {
    this.paymentNetwork = paymentNetwork;
  }

  @Deprecated
  public CreditCardPaymentMeanInfo getCCPaymentMeanInfo() {
    return this.getCreditCardPaymentMeanInfo();
  }

  /**
   * Return the payment mean info for Credit Card transaction. This is a shortcut for
   * getPaymentMeanInfo(CreditCardPaymentMeanInfo.class) (with paymentMethod check).
   * 
   * @return The CreditCardPaymentMeanInfo for this transaction
   */
  public CreditCardPaymentMeanInfo getCreditCardPaymentMeanInfo() {
    if (!PaymentMethod.CREDIT_CARD.equals(this.paymentMethod)) {
      logger.error("Payment type is not Credit Card, can not return payment mean info for credit card.");

      return null;
    }

    return this.getPaymentMeanInfo(CreditCardPaymentMeanInfo.class);
  }

  @Deprecated
  public BankTransferPaymentMeanInfo getBTPaymentMeanInfo() {
    return this.getBankTransferPaymentMeanInfo();
  }

  /**
   * Return the payment mean info for Bank transfer transaction. This is a shortcut for
   * getPaymentMeanInfo(BankTransferPaymentMeanInfo.class) (with paymentMethod check).
   * 
   * @return The BankTransferPaymentMeanInfo for this transaction
   */
  public BankTransferPaymentMeanInfo getBankTransferPaymentMeanInfo() {
    if (!PaymentMethod.BANK_TRANSFER.equals(this.paymentMethod)) {
      logger.error("Payment type is not Bank Transfer, can not return payment mean info for bank transfer.");

      return null;
    }

    return this.getPaymentMeanInfo(BankTransferPaymentMeanInfo.class);
  }

  @Deprecated
  public ToditoPaymentMeanInfo getToditoPaymentMeanInfo() {
    return this.getToditoCashPaymentMeanInfo();
  }

  /**
   * Return the payment mean info for Todito transaction. This is a shortcut for
   * getPaymentMeanInfo(ToditoPaymentMeanInfo.class) (with paymentMethod check).
   * 
   * @return The ToditoPaymentMeanInfo for this transaction
   */
  public ToditoPaymentMeanInfo getToditoCashPaymentMeanInfo() {
    if (!PaymentMethod.TODITO_CASH.equals(this.paymentMethod)) {
      logger.error("Payment type is not Todito cash, can not return payment mean info for Todito cash.");

      return null;
    }

    return this.getPaymentMeanInfo(ToditoPaymentMeanInfo.class);
  }

  /**
   * Return the payment mean info for Direct Debit transaction. This is a shortcut for
   * getPaymentMeanInfo(DirectDebitPaymentMeanInfo.class) (with paymentMethod check).
   * 
   * @return The DirectDebitPaymentMeanInfo for this transaction
   */
  public DirectDebitPaymentMeanInfo getDirectDebitPaymentMeanInfo() {
    if (!PaymentMethod.DIRECT_DEBIT.equals(this.paymentMethod)) {
      logger.error("Payment type is not Direct Debit, can not return payment mean info for Direct Debit.");

      return null;
    }

    return this.getPaymentMeanInfo(DirectDebitPaymentMeanInfo.class);
  }

  /**
   * Return the payment mean info for chargeback transaction. This is a shortcut for
   * getPaymentMeanInfo(ChargebackPaymentMeanInfo.class) (with paymentMethod check).
   * 
   * @return The ChargebackPaymentMeanInfo for this transaction
   */
  public ChargebackPaymentMeanInfo getChargebackPaymentMeanInfo() {
    if (!PaymentMethod.CHARGEBACK.equals(this.paymentMethod)) {
      logger.error("Payment type is not Chargeback, can not return payment mean info for Chargeback.");

      return null;
    }

    return this.getPaymentMeanInfo(ChargebackPaymentMeanInfo.class);
  }

  /**
   * Return the payment mean info for reversal transaction. This is a shortcut for
   * getPaymentMeanInfo(ReversalPaymentMeanInfo.class) (with paymentMethod check).
   * 
   * @return The ReversalPaymentMeanInfo for this transaction
   */
  public ReversalPaymentMeanInfo getReversalPaymentMeanInfo() {
    if (!PaymentMethod.REVERSAL.equals(this.paymentMethod)) {
      logger.error("Payment type is not Reversal, can not return payment mean info for Reversal.");

      return null;
    }

    return this.getPaymentMeanInfo(ReversalPaymentMeanInfo.class);
  }

  /**
   * @param c
   *          Class of the PaymentMeanInfo to return, this varies according to the paymentMethod value
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
    return this.operation;
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
    return this.resultCode;
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
    return this.resultMessage;
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
  public PaymentStatusValue getStatus() {
    return this.status;
  }

  /**
   * @param status
   *          the status to set
   */
  public void setStatus(PaymentStatusValue status) {
    this.status = status;
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
   * @return the refTransactionId
   */
  public String getRefTransactionId() {
    return this.refTransactionId;
  }

  /**
   * @param refTransactionId
   *          the refTransactionId to set
   */
  public void setRefTransactionId(String refTransactionId) {
    this.refTransactionId = refTransactionId;
  }

  @Deprecated
  public String getProvider() {
    return this.getPaymentNetwork();
  }

  @Deprecated
  public void setProvider(String provider) {
    this.setPaymentNetwork(provider);
  }

  /**
   * @return the providerTransactionId
   */
  public String getProviderTransactionId() {
    return providerTransactionId;
  }

  /**
   * @param providerTransactionId
   *          the providerTransactionId to set
   */
  public void setProviderTransactionId(String providerTransactionId) {
    this.providerTransactionId = providerTransactionId;
  }

  /**
   * @return the subscriptionId
   */
  public Long getSubscriptionId() {
    return this.subscriptionId;
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
   * @return the cardHolderName
   * @deprecated cardHolderName has been moved into paymentMeanInfo.cardHolderName
   */
  @Deprecated
  public String getCardHolderName() {
    if (this.paymentMeanInfo != null && PaymentMethod.CREDIT_CARD.equals(this.paymentMethod)) {
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

  @Override
  public int compareTo(TransactionAttempt val) {
    final int BEFORE = -1;
    final int EQUAL = 0;
    final int AFTER = 1;

    // this optimization is usually worthwhile,
    if (this == val) {
      return EQUAL;
    }

    int resultCmp = EQUAL;

    // test if "date" defined
    if (this.date == null && (val != null && val.date == null)) {
      resultCmp = EQUAL;
    } else if (this.date == null) {
      resultCmp = BEFORE;
    } else if (val == null || val.date == null) {
      resultCmp = AFTER;
    } else {
      resultCmp = this.date.compareTo(val.date);
    }

    return resultCmp;
  }
}