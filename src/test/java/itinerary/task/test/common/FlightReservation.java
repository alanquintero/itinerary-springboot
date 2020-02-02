/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.test.common;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import itinerary.task.model.Reservation;

public class FlightReservation extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightReservation.class);

	private int port;

	private TestRestTemplate restTemplate;

	private ResponseEntity<String> response;

	private Reservation reservation;

	public ResponseEntity<String> getResponse() {
		return response;
	}

	public FlightReservation(int port, TestRestTemplate restTemplate, Reservation reservation) {
		this.port = port;
		this.restTemplate = restTemplate;
		this.reservation = reservation;
	}

	public void run() {
		try {
			response = restTemplate.postForEntity(new URL("http://localhost:" + port + "/reserveFlight").toString(),
					reservation, String.class);
		} catch (RestClientException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
