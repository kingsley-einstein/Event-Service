package com.eventmanager.events.errors;

@SuppressWarnings("serial")
public class Error extends RuntimeException {
  private Integer statusCode;

  public Error(Integer statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }

  public Integer getStatusCode() {
    return statusCode;
  }
}
