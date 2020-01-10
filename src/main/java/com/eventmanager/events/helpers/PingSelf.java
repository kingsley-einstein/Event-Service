package com.eventmanager.events.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PingSelf {
  @Autowired RestTemplate template;

  @Scheduled(fixedRate = (60000 * 5))
  public void run() {
    template.getForEntity("https://event-manager-main.herokuapp.com", Object.class);
  }
}
