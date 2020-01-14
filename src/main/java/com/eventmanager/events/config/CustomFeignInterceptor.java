package com.eventmanager.events.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class CustomFeignInterceptor implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate template) {
    System.out.println(String.format("Making a call to --- %s", template.url()));
  }
}
