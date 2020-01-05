package com.eventmanager.events.helpers;

import java.util.Date;
import java.util.List;
// import java.util.TimerTask;

import com.eventmanager.events.model.Event;
import com.eventmanager.events.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeleteConcluded {
  @Autowired private EventRepository repository;

  @Scheduled(fixedRate = (60000 * 10))
  public void run() {
    // Get all events
    List<Event> events = repository.findAll();
    // Loop through if size is more than 0
    if (events.size() > 0) {
      events.forEach((event) -> {
        // If event's concluding time is before current time, delete the event
        if (event.getConcludingTime().before(new Date(System.currentTimeMillis()))) {
          repository.delete(event);
        }
      });
    }
  }
}
