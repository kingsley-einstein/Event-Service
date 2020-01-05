package com.eventmanager.events.errors.handlers;

import com.eventmanager.events.errors.Error;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignHandler implements ErrorDecoder {
  @Override
  public Exception decode(String methodKey, Response response) {
    switch(response.status()) {
      case 400:
        return new Error(400, "The client responded with a 400");
      case 401:
        return new Error(401, "The client responded with a 401");
      case 500:
        return new Error(500, "Internal server error");
    }
    return new Error(500, "Internal server error");
  }
}
