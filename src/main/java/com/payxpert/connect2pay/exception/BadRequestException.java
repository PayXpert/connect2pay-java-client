package com.payxpert.connect2pay.exception;

/**
 * Thrown when a request validation failed.
 * 
 * @author jsh
 * 
 */
public class BadRequestException extends Exception {
  private static final long serialVersionUID = 5432596477571866208L;

  public BadRequestException() {
    super();
  }

  public BadRequestException(String message) {
    super(message);
  }
}
