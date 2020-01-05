package com.eventmanager.events.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.eventmanager.events.client.AuthClient;
import com.eventmanager.events.client.mappings.Auth;
import com.eventmanager.events.errors.Error;
import com.eventmanager.events.model.Event;
import com.eventmanager.events.repository.EventRepository;
import com.eventmanager.events.responses.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EventController {
  @Autowired private EventRepository repository;
  
  @Autowired private AuthClient client;

  @PostMapping("/event")
  public ResponseEntity<Response<Event>> create(
    @RequestBody Event event, 
    @RequestHeader(value = "Authorization") String authorization
  ) {
    Response<Auth> authResponse = client.getLoggedUser(authorization);
    Auth auth = authResponse.getBody();
    Event e = repository.save(
      new Event(UUID.randomUUID(), event.getTitle(), event.getVenue(), auth.getId(), event.getStartTime(), event.getConcludingTime())
    );
    return new ResponseEntity<Response<Event>>(new Response<Event>(201, e), HttpStatus.CREATED);
  }

  @GetMapping("/events/byCreator/{page}")
  public ResponseEntity<Response<List<Event>>> getEventsByUser(
    @RequestHeader(value = "Authorization") String authorization,
    @PathVariable("page") Integer page
  ) {
    Response<Auth> authResponse = client.getLoggedUser(authorization);
    Auth auth = authResponse.getBody();
    Page<Event> events = repository.findByCreator(auth.getId(), PageRequest.of(page, 10));
    return new ResponseEntity<>(
      new Response<List<Event>>(200, events.getContent()),
      HttpStatus.OK
    );
  }

  @GetMapping("/event/{id}")
  public ResponseEntity<Response<Event>> getEvent(@PathVariable("id") UUID id) {
    Event e = repository.findById(id).orElseThrow(() -> new Error(404, "Not found"));
    return new ResponseEntity<>(
      new Response<Event>(200, e),
      HttpStatus.OK
    );
  }

  @PatchMapping("/event/{id}")
  public ResponseEntity<Response<Event>> updateEvent(
    @PathVariable("id") UUID id, 
    @RequestHeader(value = "Authorization") String authorization,
    @RequestBody Event event
  ) {
    Response<Auth> authResponse = client.getLoggedUser(authorization);
    Auth auth = authResponse.getBody();
    List<Event> events = repository.findByCreator(auth.getId());
    Event e = null;
    for (int i = 0; i < events.size(); i++) {
      if (events.get(i).getId() == id) {
        e = events.get(i);
      }
    }
    if (e == null) {
      throw new Error(404, "Event doesn't exist or has been deleted");
    }
    e.setAll(event);
    Event updatedEvent = repository.save(e);
    return new ResponseEntity<Response<Event>>(
      new Response<Event>(200, updatedEvent),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/event/{id}")
  public ResponseEntity<Response<String>> deleteOne(
    @PathVariable("id") UUID id,
    @RequestHeader(value = "Authorization") String authorization
  ) {
    Response<Auth> authResponse = client.getLoggedUser(authorization);
    Auth auth = authResponse.getBody();
    List<Event> events = repository.findByCreator(auth.getId());
    events.forEach((event) -> {
      if (event.getId() == id) {
        repository.deleteById(event.getId());
      }
    });
    return new ResponseEntity<>(
      new Response<String>(200, String.format("Deleted event with id %s", id.toString())),
      HttpStatus.OK
    );
  }
  
  @DeleteMapping("/events/byCreator")
  public ResponseEntity<Response<List<String>>> deleteAllByUser(@RequestHeader(value = "Authorization") String authorization) {
    Response<Auth> authResponse = client.getLoggedUser(authorization);
    Auth auth = authResponse.getBody();
    List<Event> events = repository.findByCreator(auth.getId());
    List<String> strings = new ArrayList<>();
    events.forEach((event) -> {
      repository.deleteById(event.getId());
      strings.add(
        String.format("Deleted event with id %s", event.getId().toString())
      );
    });
    return new ResponseEntity<>(
      new Response<List<String>>(200, strings),
      HttpStatus.OK
    );
  }
}
