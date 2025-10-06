/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/itinerary-springboot
 */
package itinerary.services.controller;

import itinerary.services.service.ItineraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import itinerary.services.model.Reservation;
import itinerary.services.dto.ApiResponse;

/**
 * REST controller responsible for handling flight reservation requests.
 * <p>
 * This controller receives reservation details from clients and delegates the
 * booking process to the {@link ItineraryService}. It returns a JSON response
 * indicating whether the reservation was successful.
 * </p>
 */
@RestController
public class ReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    private final ItineraryService itineraryService;

    public ReservationController(final ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    /**
     * Handles HTTP POST requests to "/reserveFlight".
     * <p>
     * Expects a JSON payload representing a {@link Reservation} object containing
     * the flight ID, first name, and last name of the person making the reservation.
     * Delegates the booking process to the {@link ItineraryService} and returns
     * an {@link ApiResponse} indicating success or failure.
     * </p>
     *
     * @param reservation the reservation details sent by the client
     * @return an {@link ApiResponse} containing a success/failure message
     */
    @PostMapping(path = "/reserveFlight", consumes = "application/json", produces = "application/json")
    public ApiResponse reserveFlight(@RequestBody Reservation reservation) {
        LOGGER.info("ReservationController in /reserveFlight route");
        return itineraryService.reserveFlight(reservation.getFlightFk(), reservation.getFirstname(), reservation.getLastname());
    }
}
