package com.payxpert.connect2pay.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payxpert.connect2pay.client.Connect2payClient;
import com.payxpert.connect2pay.client.containers.TransactionAttempt;
import com.payxpert.connect2pay.constants.ResultCode;

/**
 * This class represents the response to a WeChat Direct Process request.
 * 
 */
public class WeChatDirectProcessResponse extends GenericResponse<WeChatDirectProcessResponse> {
  // Library internal field, not returned by the API call
  private ResultCode code;
  private String message;

  private String apiVersion;

  private Double exchangeRate;

  private String qrCode;

  private String qrCodeUrl;

  private String webSocketUrl;

  @JsonProperty("transactionID")
  private String transactionId;

  private TransactionAttempt transactionInfo;

  /**
   * @return the result code
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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * The API version for the response
   */
  public String getApiVersion() {
    return apiVersion;
  }

  public void setApiVersion(String apiVersion) {
    this.apiVersion = apiVersion;
  }

  /**
   * @return The currency exchange rate between the payment currency and RMB
   */
  public Double getExchangeRate() {
    return exchangeRate;
  }

  public void setExchangeRate(Double exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  /**
   * @return The QR code as PNG base64 encoded
   */
  public String getQrCode() {
    return qrCode;
  }

  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }

  /**
   * @return The URL to embed in a QR code
   */
  public String getQrCodeUrl() {
    return qrCodeUrl;
  }

  public void setQrCodeUrl(String qrCodeUrl) {
    this.qrCodeUrl = qrCodeUrl;
  }

  /**
   * @return The URL of the WebSocket to monitor the payment status in real time
   */
  public String getWebSocketUrl() {
    return webSocketUrl;
  }

  public void setWebSocketUrl(String webSocketUrl) {
    this.webSocketUrl = webSocketUrl;
  }

  /**
   * @return The identifier of the generated transaction, can be used to poll the transaction information
   * @see Connect2payClient#getTransactionInfo(com.payxpert.connect2pay.client.requests.TransactionInfoRequest)
   */
  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  /**
   * @return The information about the processed transaction (QuickPay mode only)
   */
  public TransactionAttempt getTransactionInfo() {
    return transactionInfo;
  }

  public void setTransactionInfo(TransactionAttempt transactionInfo) {
    this.transactionInfo = transactionInfo;
  }

}