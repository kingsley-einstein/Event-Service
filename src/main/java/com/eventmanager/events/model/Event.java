package com.eventmanager.events.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "events")
public class Event implements java.io.Serializable {
  @Id private UUID id;
  
  @NotEmpty(message = "Event title cannot be empty")
  @NotNull
  @Column(name = "title")
  private String title;
  
  @NotEmpty(message = "Event venue is required")
  @NotNull
  @Column(name = "venue", unique = true)
  private String venue;

  @Column(name = "creator") private UUID creator;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP) 
  private Date startTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP) 
  private Date concludingTime;

  // @Column(name = "concluded") private Boolean concluded;

  public Event() {}

  public Event(UUID id, String title, String venue, UUID creator, Date startTime, Date concludingTime) {
    this.id = id;
    this.title = title;
    this.venue = venue;
    this.creator = creator;
    this.startTime = startTime;
    this.concludingTime = concludingTime;
    // this.concluded = false;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public String getVenue() {
    return venue;
  }

  public UUID getCreator() {
    return creator;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setConcludingTime(Date concludingTime) {
    this.concludingTime = concludingTime;
  }

  public Date getConcludingTime() {
    return concludingTime;
  }

  // public void setConcluded(Boolean concluded) {
  //   this.concluded = concluded;
  // }

  // public Boolean getConcluded() {
  //   return concluded;
  // }

  public void setAll(Event event) {
    if (event.getTitle() != null) setTitle(event.getTitle());
    if (event.getVenue() != null) setVenue(event.getVenue());
    if (event.getStartTime() != null) setStartTime(event.getStartTime());
    if (event.getConcludingTime() != null) setConcludingTime(event.getConcludingTime());
  }

  public UUID getId() {
    return id;
  }
}