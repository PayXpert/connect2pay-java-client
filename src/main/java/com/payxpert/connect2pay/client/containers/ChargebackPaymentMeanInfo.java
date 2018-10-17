package com.payxpert.connect2pay.client.containers;

import java.util.Date;

/**
 * Information about a Chargeback.
 */
public class ChargebackPaymentMeanInfo extends PaymentMeanInfo {
  private Date date;

  private String reasonCode;

  /**
   * @return the chargeback date
   */
  public Date getDate() {
    return this.date;
  }

  /**
   * @param date
   *          the date to set
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @return The chargeback reason code
   */
  public String getReasonCode() {
    return this.reasonCode;
  }

  /**
   * @param reasonCode
   *          The reason code to set
   */
  public void setReasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
  }
}
