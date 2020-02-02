/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import itinerary.task.model.Reservation;
import itinerary.task.model.Response;
import itinerary.task.service.ItineraryService;

@RestController
public class ReservationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	private ItineraryService itineraryService;

	/**
	 * Receives the reservation details and try to book a flight.
	 * 
	 * @param Reservation
	 * @return Response
	 */
	@PostMapping(path = "/reserveFlight", consumes = "application/json", produces = "application/json")
	public Response reserveFlight(@RequestBody Reservation reservation) {
		LOGGER.info("ReservationController in /reserveFlight route");
		return itineraryService.reserveFlight(reservation);
	}

}
