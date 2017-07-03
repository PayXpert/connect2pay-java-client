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
  TRANS_REFUND("/transaction/:transactionId/refund"),
  /* */
  SUB_CANCEL("/subscription/:subscriptionID/cancel");

  private String route;

  APIRoute(String route) {
    this.route = route;
  }

  public String getRoute() {
    return this.route;
  }
}
