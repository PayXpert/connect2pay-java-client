package com.payxpert.connect2pay.constants;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The Alipay identity code types available for Alipay direct process call
 * 
 */
public enum AlipayIdentityCodeType {
  /**
   * QR code
   */
  QRCODE,

  /**
   * Bar code
   */
  BARCODE;

  @JsonValue
  public String getFormattedName() {
    return this.name().toLowerCase();
  }
}
