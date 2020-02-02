/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import itinerary.task.model.Reservation;
import itinerary.task.test.common.CommonFunctionalityForTest;
import itinerary.task.test.common.FlightReservation;
import itinerary.task.utils.Constants;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReservationControllerTest {

	// bind the above RANDOM_PORT
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CommonFunctionalityForTest commonFuncForTest;

	@BeforeEach
	public void before() {
		commonFuncForTest.createFlightData();
	}

	@AfterEach
	public void after() {
		commonFuncForTest.clearFlightData();
	}

	@Test
	public void testReserveFlightSuccess() throws Exception {
		Reservation requestBody = new Reservation();
		requestBody.setFlightId(commonFuncForTest.flightId);
		requestBody.setFirstName("Alan");
		requestBody.setLastName("Quintero");

		ResponseEntity<String> response = restTemplate.postForEntity(
				new URL("http://localhost:" + port + "/reserveFlight").toString(), requestBody, String.class);

		assertTrue(response.getBody().contains(Constants.MESSAGE_SUCCESS));
	}

	@Test
	public void testTwoUsersTryToReserveSameFlightAtTheSameTime() throws Exception {
		Reservation requestBodyUser1 = new Reservation();
		requestBodyUser1.setFlightId(commonFuncForTest.flightId);
		requestBodyUser1.setFirstName("Alan");
		requestBodyUser1.setLastName("Quintero");

		Reservation requestBodyUser2 = new Reservation();
		requestBodyUser2.setFlightId(commonFuncForTest.flightId);
		requestBodyUser2.setFirstName("Alejandra");
		requestBodyUser2.setLastName("Jauregui");

		FlightReservation flightReservationUser1 = new FlightReservation(port, restTemplate, requestBodyUser1);
		FlightReservation flightReservationUser2 = new FlightReservation(port, restTemplate, requestBodyUser2);

		flightReservationUser1.start();
		flightReservationUser2.start();

		flightReservationUser1.join();
		flightReservationUser2.join();

		if (flightReservationUser1.getResponse().getBody().contains(Constants.MESSAGE_SUCCESS)) {
			assertTrue(flightReservationUser2.getResponse().getBody().contains(Constants.MESSAGE_ALREADY_BOOKED));
		} else {
			assertTrue(flightReservationUser1.getResponse().getBody().contains(Constants.MESSAGE_ALREADY_BOOKED));
		}
	}

	@Test
	public void testTryToReserveInvalidFlight() throws Exception {
		Reservation requestBody = new Reservation();
		requestBody.setFlightId(-1);
		requestBody.setFirstName("Alan");
		requestBody.setLastName("Quintero");

		ResponseEntity<String> response = restTemplate.postForEntity(
				new URL("http://localhost:" + port + "/reserveFlight").toString(), requestBody, String.class);

		assertTrue(response.getBody().contains(Constants.MESSAGE_ALREADY_BOOKED));
	}

}
