package com.eventmanager.events.errors.handlers;

import com.eventmanager.events.errors.Error;
import com.eventmanager.events.responses.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(value = Error.class)
  public ResponseEntity<Response<String>> exception(Error error) {
    return new ResponseEntity<>(
      new Response<>(error.getStatusCode(), error.getMessage()),
      ResponseEntity.status(error.getStatusCode()).build().getStatusCode()
    );
  }
}
