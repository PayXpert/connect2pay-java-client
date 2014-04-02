package com.payxpert.connect2pay.exception;

/**
 * Thrown when the payment page application returns a 404 HTTP code.
 * 
 * @author jsh
 * 
 */
public class HttpNotFoundException extends Exception {
  private static final long serialVersionUID = -5201110405925423535L;

  public HttpNotFoundException() {
    super();
  }

  public HttpNotFoundException(String message) {
    super(message);
  }
}
