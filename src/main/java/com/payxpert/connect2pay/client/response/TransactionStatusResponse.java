package com.payxpert.connect2pay.client.response;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.payxpert.connect2pay.client.containers.CreditCardPaymentMeanInfo;
import com.payxpert.connect2pay.client.containers.PaymentMeanInfo;
import com.payxpert.connect2pay.constants.PaymentType;
import com.payxpert.connect2pay.constants.ResultCode;
import com.payxpert.connect2pay.constants.TransactionOperation;
import com.payxpert.connect2pay.constants.TransactionStatusValue;
import com.payxpert.connect2pay.utils.Utils;
import com.payxpert.connect2pay.utils.json.PaymentMeanInfoDeserializer;

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
  private PaymentType paymentType;
  @JsonDeserialize(using = PaymentMeanInfoDeserializer.class)
  private String paymentMeanInfo;
  private TransactionOperation operation;
  private String errorCode;
  private String errorMessage;
  private TransactionStatusValue status;
  @JsonProperty("transactionID")
  private Long transactionId;
  @JsonProperty("subscriptionID")
  private Long subscriptionId;
  private String ctrlCustomData;
  @JsonProperty("orderID")
  private String orderId;
  private String currency;
  private Integer amount;

  // Shopper informations
  private String shopperName;
  private String shopperAddress;
  private String shopperZipcode;
  private String shopperCity;
  private String shopperState;
  private String shopperCountryCode;
  private String shopperPhone;
  private String shopperEmail;
  private String shopperIPAddress;

  // Credit Card Transaction specific information
  private String statementDescriptor;

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
   * @return the transactionId
   */
  public Long getTransactionId() {
    return transactionId;
  }

  /**
   * @param transactionId
   *          the transactionId to set
   */
  public void setTransactionId(Long transactionId) {
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

  /**
   * @return the shopperName
   */
  public String getShopperName() {
    return shopperName;
  }

  /**
   * @param shopperName
   *          the shopperName to set
   */
  public void setShopperName(String shopperName) {
    this.shopperName = shopperName;
  }

  /**
   * @return the shopperAddress
   */
  public String getShopperAddress() {
    return shopperAddress;
  }

  /**
   * @param shopperAddress
   *          the shopperAddress to set
   */
  public void setShopperAddress(String shopperAddress) {
    this.shopperAddress = shopperAddress;
  }

  /**
   * @return the shopperZipcode
   */
  public String getShopperZipcode() {
    return shopperZipcode;
  }

  /**
   * @param shopperZipcode
   *          the shopperZipcode to set
   */
  public void setShopperZipcode(String shopperZipcode) {
    this.shopperZipcode = shopperZipcode;
  }

  /**
   * @return the shopperCity
   */
  public String getShopperCity() {
    return shopperCity;
  }

  /**
   * @param shopperCity
   *          the shopperCity to set
   */
  public void setShopperCity(String shopperCity) {
    this.shopperCity = shopperCity;
  }

  /**
   * @return the shopperState
   */
  public String getShopperState() {
    return shopperState;
  }

  /**
   * @param shopperState
   *          the shopperState to set
   */
  public void setShopperState(String shopperState) {
    this.shopperState = shopperState;
  }

  /**
   * @return the shopperCountryCode
   */
  public String getShopperCountryCode() {
    return shopperCountryCode;
  }

  /**
   * @param shopperCountryCode
   *          the shopperCountryCode to set
   */
  public void setShopperCountryCode(String shopperCountryCode) {
    this.shopperCountryCode = shopperCountryCode;
  }

  /**
   * @return the shopperPhone
   */
  public String getShopperPhone() {
    return shopperPhone;
  }

  /**
   * @param shopperPhone
   *          the shopperPhone to set
   */
  public void setShopperPhone(String shopperPhone) {
    this.shopperPhone = shopperPhone;
  }

  /**
   * @return the shopperEmail
   */
  public String getShopperEmail() {
    return shopperEmail;
  }

  /**
   * @param shopperEmail
   *          the shopperEmail to set
   */
  public void setShopperEmail(String shopperEmail) {
    this.shopperEmail = shopperEmail;
  }

  /**
   * @return the shopperIPAddress
   */
  public String getShopperIPAddress() {
    return shopperIPAddress;
  }

  /**
   * @param shopperIPAddress
   *          the shopperIPAddress to set
   */
  public void setShopperIPAddress(String shopperIPAddress) {
    this.shopperIPAddress = shopperIPAddress;
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
  public void setCardHolderName(String cardHolderName) {
  }
}
