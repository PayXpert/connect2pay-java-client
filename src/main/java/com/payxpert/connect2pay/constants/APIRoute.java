package com.payxpert.connect2pay.constants;

/**
 * Payment page API routes.
 * 
 * @author jsh
 * 
 */
public enum APIRoute {
  TRANS_PREPARE("/transaction/prepare"),
  /* */
  TRANS_STATUS("/transaction/:merchantToken/status"),
  /* */
  TRANS_DOPAY("/transaction/:customerToken"),
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
