package com.eventmanager.events.responses;

@SuppressWarnings("serial")
public class Response<T> implements java.io.Serializable {
  private Integer statusCode;

  private T body;

  public Response() {}

  public Response(Integer statusCode, T body) {
    this.statusCode = statusCode;
    this.body = body;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public T getBody() {
    return body;
  }
}
