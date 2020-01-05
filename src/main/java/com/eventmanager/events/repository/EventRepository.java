package com.eventmanager.events.repository;

import java.util.List;
import java.util.UUID;

import com.eventmanager.events.model.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
  // public List<Event> findByConcluded(Boolean concluded);
  public Page<Event> findByCreator(UUID creator, Pageable pageable);
  public List<Event> findByCreator(UUID creator);
}
