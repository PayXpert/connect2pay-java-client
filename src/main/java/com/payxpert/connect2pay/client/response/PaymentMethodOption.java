package com.payxpert.connect2pay.client.response;

public class PaymentMethodOption {

  private String name;

  private String value;

  /**
   * The name of the option
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * The value of the option
   */
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
