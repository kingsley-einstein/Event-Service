package com.eventmanager.events.client;

import java.util.UUID;

import com.eventmanager.events.config.CustomFeignClientConfig;
import com.eventmanager.events.responses.Response;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rsvp-service", configuration = CustomFeignClientConfig.class)
public interface RsvpClient {
  @DeleteMapping("/api/v1/rsvps/byEvent/{eventId}")
  public Response<?> deleteRsvps(@PathVariable("eventId") UUID id);
}
