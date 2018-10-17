package com.payxpert.connect2pay.client.response;

import java.util.List;

import com.payxpert.connect2pay.constants.PaymentMethod;

public class PaymentMethodInformation {
  private PaymentMethod paymentMethod;

  private String paymentNetwork;

  private List<String> currencies;

  private String defaultOperation;

  private List<PaymentMethodOption> options;

  /**
   * The payment method
   */
  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  /**
   * The payment network
   */
  public String getPaymentNetwork() {
    return paymentNetwork;
  }

  public void setPaymentNetwork(String paymentNetwork) {
    this.paymentNetwork = paymentNetwork;
  }

  /**
   * A list of ISO 4217 currency code for which this payment method is enabled
   */
  public List<String> getCurrencies() {
    return currencies;
  }

  public void setCurrencies(List<String> currencies) {
    this.currencies = currencies;
  }

  /**
   * The operation that will be executed by default when processing this type of method. Can be sale, authorize or
   * collection
   */
  public String getDefaultOperation() {
    return defaultOperation;
  }

  public void setDefaultOperation(String defaultOperation) {
    this.defaultOperation = defaultOperation;
  }

  /**
   * A list of payment method specific option
   */
  public List<PaymentMethodOption> getOptions() {
    return options;
  }

  public void setOptions(List<PaymentMethodOption> options) {
    this.options = options;
  }
}
