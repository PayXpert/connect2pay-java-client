package com.payxpert.connect2pay.client.containers;

import java.util.Date;

/**
 * Information about a direct debit payment mean.
 * 
 */
public class DirectDebitPaymentMeanInfo extends PaymentMeanInfo {

  private BankAccount bankAccount;

  private String statementDescriptor;

  private Date collectedAt;

  public BankAccount getBankAccount() {
    return this.bankAccount;
  }

  public void setBankAccount(BankAccount account) {
    this.bankAccount = account;
  }

  public String getStatementDescriptor() {
    return this.statementDescriptor;
  }

  public void setStatementDescriptor(String statementDescriptor) {
    this.statementDescriptor = statementDescriptor;
  }

  public Date getCollectedAt() {
    return collectedAt;
  }

  public void setCollectedAt(Date collectedAt) {
    this.collectedAt = collectedAt;
  }
}
