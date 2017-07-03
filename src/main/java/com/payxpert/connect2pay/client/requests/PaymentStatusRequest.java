package com.payxpert.connect2pay.client.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.sf.oval.constraint.NotNull;

/**
 * Request for the payment status API call.
 * 
 * @author jsh
 * 
 */
public class PaymentStatusRequest extends GenericRequest<PaymentStatusRequest> {
  @NotNull
  @JsonIgnore
  private String merchantToken;

  @Override
  protected PaymentStatusRequest getThis() {
    return this;
  }

  /**
   * @return The merchantToken
   */
  public String getMerchantToken() {
    return this.merchantToken;
  }

  /**
   * @param merchantToken
   *          the merchantToken to set
   * 
   * @return The current request for method chaining
   */
  public PaymentStatusRequest setMerchantToken(String merchantToken) {
    this.merchantToken = merchantToken;
    return getThis();
  }
}
