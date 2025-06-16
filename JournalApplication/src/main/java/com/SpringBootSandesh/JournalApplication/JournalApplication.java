package com.SpringBootSandesh.JournalApplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
		log.info("Application Running Successfully without any error");
		log.trace("All levels of log activated Successfully!");

		/*
		log.debug("Lets start Debugging the Tester in Spring Boot");
		try {
			int b = 10/2;
			log.info("Debug correct with a result of {}", b);
		}
		catch (Exception e){
			log.error("Some Error encountered with message {}", e.getMessage());
		}
		*/

	}
}
