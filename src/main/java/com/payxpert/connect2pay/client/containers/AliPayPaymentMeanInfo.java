package com.payxpert.connect2pay.client.containers;

import java.math.BigDecimal;

/**
 * Information from the AliPay callback
 */
public class AliPayPaymentMeanInfo extends PaymentMeanInfo {

  /**
   * Total fee in cents
   */
  private Long totalFee;
  private BigDecimal exchangeRate;

  public Long getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Long totalFee) {
    this.totalFee = totalFee;
  }

  public BigDecimal getExchangeRate() {
    return exchangeRate;
  }

  public void setExchangeRate(BigDecimal exchangeRate) {
    this.exchangeRate = exchangeRate;
  }
}
