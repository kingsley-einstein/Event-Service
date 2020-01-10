package com.eventmanager.events;

import com.eventmanager.events.errors.handlers.FeignHandler;

// import java.util.Timer;

// import com.eventmanager.events.helpers.DeleteConcluded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
// import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableScheduling
public class EventServiceApplication {
	@Bean
	public FeignHandler feignErrorHandler() {
		return new FeignHandler();
	}

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(EventServiceApplication.class, args);
		// Timer t = new Timer();
		// t.schedule(new DeleteConcluded(), 10000);
	}
}
