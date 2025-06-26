package com.example.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduledJobsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduledJobsServiceApplication.class, args);
	}

}
