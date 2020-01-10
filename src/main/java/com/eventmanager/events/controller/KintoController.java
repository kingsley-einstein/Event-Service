package com.eventmanager.events.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Provides healthcheck route for Kintohub
@RestController
@RequestMapping("/")
public class KintoController {
  @GetMapping
  public ResponseEntity<Map<String, Object>> ping() {
    Map<String, Object> map = new HashMap<>();
    map.put("status", "UP");
    return new ResponseEntity<>(map, HttpStatus.OK);
  }
}
