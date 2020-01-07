package com.eventmanager.events;

import static org.junit.Assert.assertNotEquals;

import java.util.Date;
import java.util.UUID;

import com.eventmanager.events.model.Event;
import com.eventmanager.events.repository.EventRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceApplicationTests {

	@Autowired private EventRepository repository;

	@Test
	public void itCreatesAnEvent() {
		Event e = new Event(
			UUID.randomUUID(), 
			"Test Title", 
			"Test Venue", 
			UUID.randomUUID(), 
			new Date(System.currentTimeMillis()), 
			new Date(System.currentTimeMillis())
		);
		Event saved = repository.save(e);
		assertNotEquals(saved, null);
	}

}
