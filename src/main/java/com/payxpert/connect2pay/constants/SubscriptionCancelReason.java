package com.payxpert.connect2pay.constants;

/**
 * Valid Subscription Cancel reasons to use in Subscription Cancel API call.
 * 
 * @author jsh
 * 
 */
public enum SubscriptionCancelReason {
  /**
   * Bank denial
   */
  BANK_DENIAL(1000, "Bank denial"),
  /**
   * Canceled due to refund
   */
  REFUNDED(1001, "Canceled due to refund"),
  /**
   * Canceled due to retrieval request
   */
  RETRIEVAL(1002, "Canceled due to retrieval request"),
  /**
   * Cancellation letter sent by bank
   */
  BANK_LETTER(1003, "Cancellation letter sent by bank"),
  /**
   * Chargeback
   */
  CHARGEBACK(1004, "Chargeback"),
  /**
   * Company account closed
   */
  COMPANY_ACCOUNT_CLOSED(1005, "Company account closed"),
  /**
   * Site account closed
   */
  WEBSITE_ACCOUNT_CLOSED(1006, "Site account closed"),
  /**
   * Didn't like the site
   */
  DID_NOT_LIKE(1007, "Didn't like the site"),
  /**
   * Disagree ('Did not do it' or 'Do not recognize the transaction')
   */
  DISAGREE(1008, "Disagree ('Did not do it' or 'Do not recognize the transaction')"),
  /**
   * Fraud from webmaster
   */
  WEBMASTER_FRAUD(1009, "Fraud from webmaster"),
  /**
   * I could not get in to the site
   */
  COULD_NOT_GET_INTO(1010, "I could not get in to the site"),
  /**
   * No problem, just moving on
   */
  NO_PROBLEM(1011, "No problem, just moving on"),
  /**
   * Not enough updates
   */
  NOT_UPDATED(1012, "Not enough updates"),
  /**
   * Problems with the movies/videos
   */
  TECH_PROBLEM(1013, "Problems with the movies/videos"),
  /**
   * Site was too slow
   */
  TOO_SLOW(1014, "Site was too slow"),
  /**
   * The site did not work
   */
  DID_NOT_WORK(1015, "The site did not work"),
  /**
   * Too expensive
   */
  TOO_EXPENSIVE(1016, "Too expensive"),
  /**
   * Un-authorized signup by family member
   */
  UNAUTH_FAMILLY(1017, "Un-authorized signup by family member"),
  /**
   * Undetermined reasons
   */
  UNDETERMINED(1018, "Undetermined reasons"),
  /**
   * Webmaster requested to cancel
   */
  WEBMASTER_REQUESTED(1019, "Webmaster requested to cancel"),
  /**
   * I haven't received my item
   */
  NOTHING_RECEIVED(1020, "I haven't received my item"),
  /**
   * The item was damaged or defective
   */
  DAMAGED(1021, "The item was damaged or defective"),
  /**
   * The box was empty
   */
  EMPTY_BOX(1022, "The box was empty"),
  /**
   * The order was incomplete
   */
  INCOMPLETE_ORDER(1023, "The order was incomplete");

  private Integer code;
  private String message;

  SubscriptionCancelReason(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public SubscriptionCancelReason getValue() {
    return SubscriptionCancelReason.valueOf(this.name());
  }

  public String getMessage() {
    return this.message;
  }

  public Integer getCode() {
    return this.code;
  }

  public static SubscriptionCancelReason valueOf(Integer codeValue) {
    for (SubscriptionCancelReason reason : SubscriptionCancelReason.values()) {
      if (reason.code.equals(codeValue)) {
        return reason;
      }
    }
    return null;
  }

  public static boolean isValidCode(Integer code) {
    return (valueOf(code) != null);
  }
}
