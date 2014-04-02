package com.payxpert.connect2pay.utils;

import com.payxpert.connect2pay.constants.SubscriptionType;

/**
 * This is a utility class to compute the values of a part payment. Given a
 * global amount and a number of payment, this class will compute the values to
 * use to create an "on the fly" subscription.<br>
 * To split a payment of €90.90 into 3 payment each months, use:
 * 
 * <pre>
 * <code>
 *   PartPayment pp = new PartPayment(9090, 3);
 *   pp.compute();
 *   ...
 *   // Then later use this in the transaction request
 *   request.setAmount(pp.getInitialAmount());
 *   request.setSubscriptionType(pp.getSubscriptionType());
 *   request.setRebillAmount(pp.getRebillAmount());
 *   request.setRebillMaxIteration(pp.getMaxIterations());
 *   request.setRebillPeriod("P1M");
 * </code>
 * </pre>
 * 
 * @author jsh
 * 
 */
public class PartPayment {
  private boolean isResultValid = false;
  private int initialAmount;
  private int rebillAmount;
  private int maxIterations;
  private final SubscriptionType subscriptionType = SubscriptionType.NORMAL;

  private int amount;
  private int partNumber;

  public PartPayment(int amount, int partNumber) {
    this.amount = amount;
    this.partNumber = partNumber;
    this.maxIterations = this.partNumber - 1;
    this.isResultValid = false;
  }

  public boolean isResultValid() {
    return isResultValid;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.isResultValid = false;
    this.amount = amount;
  }

  public int getPartNumber() {
    return partNumber;
  }

  public void setPartNumber(int partNumber) {
    this.isResultValid = false;
    this.partNumber = partNumber;
    this.maxIterations = this.partNumber - 1;
  }

  public int getInitialAmount() {
    return initialAmount;
  }

  public int getRebillAmount() {
    return rebillAmount;
  }

  public int getMaxIterations() {
    return maxIterations;
  }

  public SubscriptionType getSubscriptionType() {
    return subscriptionType;
  }

  /**
   * Calculate how to split an amount into several parts
   * 
   * @return true if calculate success, otherwise failed
   */
  public boolean compute() {
    if (partNumber > 1 && amount >= partNumber) {
      this.rebillAmount = (int) Math.floor(amount / partNumber);
      this.initialAmount = this.rebillAmount + (amount % partNumber);
      this.isResultValid = true;
      return this.isResultValid;
    }
    this.isResultValid = false;
    return this.isResultValid;
  }
}
