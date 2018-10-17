package com.payxpert.connect2pay.client.response;

import java.util.List;

import com.payxpert.connect2pay.constants.ResultCode;

/**
 * This class represents the response to a account information request.
 * 
 */
public class AccountInformationResponse extends GenericResponse<AccountInformationResponse> {
  // Library internal field, not returned by the API call
  private ResultCode code;
  private String message;

  private String apiVersion;

  private String name;

  private Boolean displayTerms;

  private String termsUrl;

  private String supportUrl;

  private Integer maxAttempts;

  private String notificationSenderName;

  private String notificationSenderEmail;

  private Boolean notificationOnSuccess;

  private Boolean notificationOnFailure;

  private Boolean merchantNotification;

  private String merchantNotificationTo;

  private String merchantNotificationLang;

  private List<PaymentMethodInformation> paymentMethods;

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
   * The displayed name of the account.
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Indicates if Terms and conditions must be acknowledged on the payment page by the shopper.
   */
  public Boolean getDisplayTerms() {
    return displayTerms;
  }

  public void setDisplayTerms(Boolean displayTerms) {
    this.displayTerms = displayTerms;
  }

  /**
   * Terms and conditions URL.
   */
  public String getTermsUrl() {
    return termsUrl;
  }

  public void setTermsUrl(String termsUrl) {
    this.termsUrl = termsUrl;
  }

  /**
   * URL of customer support.
   */
  public String getSupportUrl() {
    return supportUrl;
  }

  public void setSupportUrl(String supportUrl) {
    this.supportUrl = supportUrl;
  }

  /**
   * The number of attempts allowed to process the payment in case of failure.
   */
  public Integer getMaxAttempts() {
    return maxAttempts;
  }

  public void setMaxAttempts(Integer maxAttempts) {
    this.maxAttempts = maxAttempts;
  }

  /**
   * Name displayed in customers notification emails.
   */
  public String getNotificationSenderName() {
    return notificationSenderName;
  }

  public void setNotificationSenderName(String notificationSenderName) {
    this.notificationSenderName = notificationSenderName;
  }

  /**
   * Email displayed in customers notification emails.
   */
  public String getNotificationSenderEmail() {
    return notificationSenderEmail;
  }

  public void setNotificationSenderEmail(String notificationSenderEmail) {
    this.notificationSenderEmail = notificationSenderEmail;
  }

  /**
   * Indicates if a notification is sent to the shopper in case of payment success.
   */
  public Boolean getNotificationOnSuccess() {
    return notificationOnSuccess;
  }

  public void setNotificationOnSuccess(Boolean notificationOnSuccess) {
    this.notificationOnSuccess = notificationOnSuccess;
  }

  /**
   * Indicates if a notification is sent to the shopper in case of payment failure.
   */
  public Boolean getNotificationOnFailure() {
    return notificationOnFailure;
  }

  public void setNotificationOnFailure(Boolean notificationOnFailure) {
    this.notificationOnFailure = notificationOnFailure;
  }

  /**
   * Indicates if a notification is sent to the merchant after a payment.
   */
  public Boolean getMerchantNotification() {
    return merchantNotification;
  }

  public void setMerchantNotification(Boolean merchantNotification) {
    this.merchantNotification = merchantNotification;
  }

  /**
   * Email used to send merchant notification.
   */
  public String getMerchantNotificationTo() {
    return merchantNotificationTo;
  }

  public void setMerchantNotificationTo(String merchantNotificationTo) {
    this.merchantNotificationTo = merchantNotificationTo;
  }

  /**
   * The language used in merchant email notification (ISO-639 two letters code).
   */
  public String getMerchantNotificationLang() {
    return merchantNotificationLang;
  }

  public void setMerchantNotificationLang(String merchantNotificationLang) {
    this.merchantNotificationLang = merchantNotificationLang;
  }

  /**
   * A list of payment methods available for the account.
   */
  public List<PaymentMethodInformation> getPaymentMethods() {
    return paymentMethods;
  }

  public void setPaymentMethods(List<PaymentMethodInformation> paymentMethods) {
    this.paymentMethods = paymentMethods;
  }
}