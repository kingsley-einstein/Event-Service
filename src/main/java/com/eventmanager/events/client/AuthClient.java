package com.eventmanager.events.client;

import com.eventmanager.events.client.mappings.Auth;
import com.eventmanager.events.responses.Response;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "auth-service")
public interface AuthClient {
  @GetMapping("/api/v1/auth")
  public Response<Auth> getLoggedUser(@RequestHeader(value = "Authorization") String authorization);
}
