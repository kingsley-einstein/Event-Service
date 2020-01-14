package com.eventmanager.events.client;

import com.eventmanager.events.client.mappings.Auth;
import com.eventmanager.events.config.CustomFeignClientConfig;
import com.eventmanager.events.responses.Response;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service:80", configuration = CustomFeignClientConfig.class)
public interface AuthClient {
  @GetMapping("/api/v1/auth")
  public Response<Auth> getLoggedUser(@RequestHeader(value = "Authorization") String authorization);
}
