package com.payxpert.connect2pay.client.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payxpert.connect2pay.constants.APIRoute;
import com.payxpert.connect2pay.constants.ResultCode;

/**
 * This class represents the response received to the transaction prepare call.
 * 
 * @author jsh
 * 
 */
public class TransactionResponse extends GenericResponse<TransactionResponse> {
  private ResultCode code;
  private String message;
  private String customerToken;
  private String merchantToken;

  @JsonIgnore
  private String serviceURL;

  /**
   * @return the code
   */
  public ResultCode getCode() {
    return code;
  }

  /**
   * @param code
   *          the code to set
   */
  public void setCode(ResultCode code) {
    this.code = code;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message
   *          the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the customerToken
   */
  public String getCustomerToken() {
    return customerToken;
  }

  /**
   * @param customerToken
   *          the customerToken to set
   */
  public void setCustomerToken(String customerToken) {
    this.customerToken = customerToken;
  }

  /**
   * @return the merchantToken
   */
  public String getMerchantToken() {
    return merchantToken;
  }

  /**
   * @param merchantToken
   *          the merchantToken to set
   */
  public void setMerchantToken(String merchantToken) {
    this.merchantToken = merchantToken;
  }

  /**
   * @return the serviceURL
   */
  public String getServiceURL() {
    return serviceURL;
  }

  /**
   * @param serviceURL
   *          the serviceURL to set
   */
  public void setServiceURL(String serviceURL) {
    if (serviceURL != null) {
      this.serviceURL = serviceURL.replaceAll("/+$", "");
    }
  }

  /**
   * Get the full URL to redirect the customer to.
   * 
   * @return The payment page URL or null if missing information
   */
  public String getCustomerRedirectURL() {
    return this.getCustomerRedirectURL(true);
  }

  /**
   * Get the URL to redirect the customer to.
   * 
   * @param includeHost
   *          Whether or not to preprend the host to the returned URL
   * @return The payment page URL or null if missing information
   */
  public String getCustomerRedirectURL(Boolean includeHost) {
    if (this.customerToken != null) {
      String prefix = "";
      if (this.serviceURL != null && includeHost) {
        prefix = this.serviceURL;
      }

      return prefix + APIRoute.TRANS_DOPAY.getRoute().replaceAll(":customerToken", this.getCustomerToken());
    }

    return null;
  }

  /**
   * Determine if the current response is a success
   * 
   * @return True if the processing was successful, false otherwise
   */
  public Boolean isSuccessful() {
    return (this.code != null && ResultCode.SUCCESS.equals(this.code));
  }
}
