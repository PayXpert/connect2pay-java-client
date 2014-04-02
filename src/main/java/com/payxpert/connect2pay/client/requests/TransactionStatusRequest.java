package com.payxpert.connect2pay.client.requests;

import net.sf.oval.constraint.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Request for the transaction status API call.
 * 
 * @author jsh
 * 
 */
public class TransactionStatusRequest extends GenericRequest<TransactionStatusRequest> {
  @NotNull
  @JsonIgnore
  private String merchantToken;

  @Override
  protected TransactionStatusRequest getThis() {
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
  public TransactionStatusRequest setMerchantToken(String merchantToken) {
    this.merchantToken = merchantToken;
    return getThis();
  }
}
