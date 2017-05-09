package com.payxpert.connect2pay.client.containers;

public class ToditoPaymentMeanInfo extends PaymentMeanInfo {
  private String cardNumber;

  /**
   * @return the cardNumber
   */
  public String getCardNumber() {
    return this.cardNumber;
  }

  /**
   * @param cardNumber
   *          the cardNumber to set
   */
  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }
}
