package com.payxpert.connect2pay.constants;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The WeChat modes available for WeChat direct process call
 * 
 */
public enum WeChatPaymentMode {
  /**
   * Native mode: the payer scans the merchant's QR codes
   */
  NATIVE,

  /**
   * QuickPay mode: The merchant scans the payer's QR code
   */
  QUICKPAY,

  /**
   * SDK mode: to be used for in-app mode
   */
  SDK,

  /**
   * MiniProgram mode: used when the payment must be performed from WeChat Mini Program
   */
  MINIPROGRAM;

  @JsonValue
  public String getFormattedName() {
    return this.name().toLowerCase();
  }
}
