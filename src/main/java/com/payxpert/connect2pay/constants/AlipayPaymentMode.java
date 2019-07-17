package com.payxpert.connect2pay.constants;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The Alipay modes available for Alipay direct process call
 * 
 */
public enum AlipayPaymentMode {
  /**
   * POS mode: the payer scans the merchant's QR codes
   */
  POS,

  /**
   * APP mode: The merchant scans the payer's QR code
   */
  APP,

  /**
   * SDK mode: to be used for in-app mode
   */
  SDK;

  @JsonValue
  public String getFormattedName() {
    return this.name().toLowerCase();
  }
}
