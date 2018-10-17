package com.payxpert.connect2pay.constants;

/**
 * Payment page API routes.
 * 
 * @author jsh
 * 
 */
public enum APIRoute {
  PAYMENT_PREPARE("/payment/prepare"),
  /* */
  PAYMENT_STATUS("/payment/:merchantToken/status"),
  /* */
  PAYMENT_DOPAY("/payment/:customerToken"),
  /* */
  TRANS_INFO("/transaction/:transactionId/info"),
  /* */
  TRANS_REFUND("/transaction/:transactionId/refund"),
  /* */
  TRANS_REFUND_CONFIRM("/transaction/:transactionId/refund/confirm"),
  /* */
  TRANS_REBILL("/transaction/:transactionId/rebill"),
  /* */
  TRANS_CANCEL("/transaction/:transactionId/cancel"),
  /* */
  WECHAT_DIRECT("/payment/:customerToken/process/wechat/direct"),
  /* */
  SUB_CANCEL("/subscription/:subscriptionID/cancel"),
  /* */
  ACCOUNT_INFO("/account");

  private String route;

  APIRoute(String route) {
    this.route = route;
  }

  public String getRoute() {
    return this.route;
  }
}
