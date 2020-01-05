package com.eventmanager.events.client.mappings;

import java.util.UUID;

@SuppressWarnings("serial")
public class Auth implements java.io.Serializable {
  private UUID id;

  private String email;

  public Auth() {}

  public Auth(UUID id, String email) {
    this.id = id;
    this.email = email;
  }

  public UUID getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }
}
