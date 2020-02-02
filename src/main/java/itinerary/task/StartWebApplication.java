/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Run this class to start the application at http://localhost:8080/
 */
@SpringBootApplication(scanBasePackages = "itinerary.task")
public class StartWebApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(StartWebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StartWebApplication.class, args);
		LOGGER.info("Application started successfully!!!");
	}

}
