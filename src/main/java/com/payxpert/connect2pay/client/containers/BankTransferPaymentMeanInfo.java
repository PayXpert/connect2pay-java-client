package com.payxpert.connect2pay.client.containers;

public class BankTransferPaymentMeanInfo extends PaymentMeanInfo {

  private BankAccount sender;
  private BankAccount recipient;

  public BankAccount getSender() {
    return this.sender;
  }

  public void setSender(BankAccount sender) {
    this.sender = sender;
  }

  public BankAccount getRecipient() {
    return this.recipient;
  }

  public void setRecipient(BankAccount recipient) {
    this.recipient = recipient;
  }
}
