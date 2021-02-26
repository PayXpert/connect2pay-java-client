package com.payxpert.connect2pay.client.containers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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

  private String currency;

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

  private Integer refundedAmount;

  @JsonProperty("providerTransactionID")
  private String providerTransactionId;

  @JsonProperty("subscriptionID")
  private Long subscriptionId;

  @JsonProperty("orderID")
  private String orderId;

  @JsonProperty("paymentID")
  private String paymentId;

  private String paymentMerchantToken;

  private String orderDescription;

  private Boolean isTest;

  private List<TransactionLog> logs;

  private List<Notification> notifications;

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

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
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
   * @return {@link CreditCardPaymentMeanInfo} for this transaction,
   * {@code null} if {@link TransactionAttempt#paymentMethod} is not equal to {@link PaymentMethod#CREDIT_CARD}
   */
  public CreditCardPaymentMeanInfo getCreditCardPaymentMeanInfo() {
    return this.getPaymentMeanInfo(CreditCardPaymentMeanInfo.class, PaymentMethod.CREDIT_CARD);
  }

  @Deprecated
  public BankTransferPaymentMeanInfo getBTPaymentMeanInfo() {
    return this.getBankTransferPaymentMeanInfo();
  }

  /**
   * @return {@link BankTransferPaymentMeanInfo} for this transaction,
   * {@code null} if {@link TransactionAttempt#paymentMethod} is not equal to {@link PaymentMethod#BANK_TRANSFER}
   */
  public BankTransferPaymentMeanInfo getBankTransferPaymentMeanInfo() {
    return this.getPaymentMeanInfo(BankTransferPaymentMeanInfo.class, PaymentMethod.BANK_TRANSFER);
  }

  @Deprecated
  public ToditoPaymentMeanInfo getToditoPaymentMeanInfo() {
    return this.getToditoCashPaymentMeanInfo();
  }

  /**
   * @return {@link ToditoPaymentMeanInfo} for this transaction,
   * {@code null} if {@link TransactionAttempt#paymentMethod} is not equal to {@link PaymentMethod#TODITO_CASH}
   */
  public ToditoPaymentMeanInfo getToditoCashPaymentMeanInfo() {
    return this.getPaymentMeanInfo(ToditoPaymentMeanInfo.class, PaymentMethod.TODITO_CASH);
  }

  /**
   * @return {@link DirectDebitPaymentMeanInfo} for this transaction,
   * {@code null} if {@link TransactionAttempt#paymentMethod} is not equal to {@link PaymentMethod#DIRECT_DEBIT}
   */
  public DirectDebitPaymentMeanInfo getDirectDebitPaymentMeanInfo() {
    return this.getPaymentMeanInfo(DirectDebitPaymentMeanInfo.class, PaymentMethod.DIRECT_DEBIT);
  }

  /**
   * @return {@link ChargebackPaymentMeanInfo} for this transaction,
   * {@code null} if {@link TransactionAttempt#paymentMethod} is not equal to {@link PaymentMethod#CHARGEBACK}
   */
  public ChargebackPaymentMeanInfo getChargebackPaymentMeanInfo() {
    return this.getPaymentMeanInfo(ChargebackPaymentMeanInfo.class, PaymentMethod.CHARGEBACK);
  }

  /**
   * @return {@link ReversalPaymentMeanInfo} for this transaction,
   * {@code null} if {@link TransactionAttempt#paymentMethod} is not equal to {@link PaymentMethod#REVERSAL}
   */
  public ReversalPaymentMeanInfo getReversalPaymentMeanInfo() {
    return this.getPaymentMeanInfo(ReversalPaymentMeanInfo.class, PaymentMethod.REVERSAL);
  }

  /**
   * @return {@link AliPayPaymentMeanInfo} for this transaction,
   * {@code null} if {@link TransactionAttempt#paymentMethod} is not equal to {@link PaymentMethod#ALIPAY}
   */
  public AliPayPaymentMeanInfo getAliPayPaymentMeanInfo() {
    return this.getPaymentMeanInfo(AliPayPaymentMeanInfo.class, PaymentMethod.ALIPAY);
  }

  /**
   * @return {@link WeChatPaymentMeanInfo} for this transaction,
   * {@code null} if {@link TransactionAttempt#paymentMethod} is not equal to {@link PaymentMethod#WECHAT}
   */
  public WeChatPaymentMeanInfo getWeChatPaymentMeanInfo() {
    return this.getPaymentMeanInfo(WeChatPaymentMeanInfo.class, PaymentMethod.WECHAT);
  }

  /**
   * @return specific {@link PaymentMeanInfo} for this transaction,
   * {@code null} if {@link TransactionAttempt#paymentMethod} is not equal to {@code expectedPaymentMethod}
   */
  private <T extends PaymentMeanInfo> T getPaymentMeanInfo(final Class<T> paymentMeanInfoClass,
                                                           final PaymentMethod expectedPaymentMethod) {
    if (this.paymentMethod == expectedPaymentMethod) {
      return this.getPaymentMeanInfo(paymentMeanInfoClass);
    }

    logger.error("Cannot return payment mean info for [{}] because payment method is: [{}]", expectedPaymentMethod, this.paymentMethod);
    return null;
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
   * @return The amount already refunded on the transaction if applicable
   */
  public Integer getRefundedAmount() {
    return refundedAmount;
  }

  public TransactionAttempt setRefundedAmount(Integer refundedAmount) {
    this.refundedAmount = refundedAmount;
    return this;
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
    return this.providerTransactionId;
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

  /**
   * @return the merchant internal unique order identifier as provided during payment creation
   */
  public String getOrderId() {
    return this.orderId;
  }

  /**
   * Sets the merchant internal unique order identifier as provided during payment creation
   *
   * @param orderId
   *          the orderId to set
   */
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  /**
   * @return The Payment identifier this transaction is related to
   */
  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  /**
   * @return The merchant token associated with the payment this transaction belongs to
   */
  public String getPaymentMerchantToken() {
    return paymentMerchantToken;
  }

  public TransactionAttempt setPaymentMerchantToken(String paymentMerchantToken) {
    this.paymentMerchantToken = paymentMerchantToken;
    return this;
  }

  /**
   * @return the description of the product purchased by the customer as provided during payment creation
   */
  public String getOrderDescription() {
    return this.orderDescription;
  }

  /**
   * Sets the description of the product purchased by the customer as provided during payment creation
   *
   * @param orderDescription
   *          the description to set
   */
  public void setOrderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
  }

  /**
   * @return If the transaction was executed as a test or not
   */
  public Boolean getTest() {
    return isTest;
  }

  public void setTest(Boolean test) {
    isTest = test;
  }

  /**
   * Usage of transaction logs requires special permissions, thus the list will usually be empty.
   *
   * @return the transaction logs
   */
  public List<TransactionLog> getLogs() {
    return this.logs;
  }

  /**
   * @param logs
   *          the TransactionLogs to set
   */
  public void setLogs(List<TransactionLog> logs) {
    this.logs = logs;
  }

  /**
   * Usage of notifications requires special permissions, thus the list will usually be empty.
   *
   * @return the notifications
   */
  public List<Notification> getNotifications() {
    return this.notifications;
  }

  /**
   * @param notifications
   *          the Notifications to set
   */
  public void setNotifications(List<Notification> notifications) {
    this.notifications = notifications;
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