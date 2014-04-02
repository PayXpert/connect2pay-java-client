package com.payxpert.connect2pay.exception;

/**
 * Thrown when the payment page application returns a 403 HTTP code.
 * 
 * @author jsh
 * 
 */
public class HttpForbiddenException extends Exception {
  private static final long serialVersionUID = -4454814230661953346L;

  public HttpForbiddenException() {
    super();
  }

  public HttpForbiddenException(String message) {
    super(message);
  }
}
