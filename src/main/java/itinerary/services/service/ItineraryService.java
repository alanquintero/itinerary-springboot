/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/itinerary-springboot
 */
package itinerary.services.service;

import java.time.LocalDateTime;
import java.util.List;

import itinerary.services.model.Flight;
import itinerary.services.projection.FlightProjection;
import itinerary.services.repository.FlightRepository;
import itinerary.services.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import itinerary.services.model.Reservation;
import itinerary.services.dto.ApiResponse;

/**
 * Service class responsible for managing flight itineraries and reservations.
 * <p>
 * Provides business logic for retrieving available flights and booking reservations.
 * Uses {@link FlightRepository} to access flight data and {@link ReservationRepository}
 * to manage reservations.
 * </p>
 */
@Service
public class ItineraryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryService.class);

    private final FlightRepository flightRepository;
    private final ReservationRepository reservationRepository;

    public ItineraryService(final FlightRepository flightRepository, final ReservationRepository reservationRepository) {
        this.flightRepository = flightRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<FlightProjection> getAvailableFlights() {
        LOGGER.info("ItineraryService::getAvailableFlights");
        return flightRepository.findAvailableFlights();
    }

    @Transactional
    public ApiResponse reserveFlight(final Long flightId, final String firstName, final String lastName) {
        final ApiResponse apiResponse = new ApiResponse();
        final Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found with id " + flightId));

        if (flight.isBook()) {
            LOGGER.info("The Flight {} is not available", flight.getId());
            apiResponse.setResponse("already-book", "We're sorry! The flight has already been reserved by a faster user...");
            return apiResponse;
        }

        // Create reservation
        final Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setFirstname(firstName);
        reservation.setLastname(lastName);
        reservation.setDateBooked(LocalDateTime.now());

        // Save reservation
        reservationRepository.save(reservation);

        // Update flight as booked
        flight.setBook(true);
        flightRepository.save(flight);

        apiResponse.setResponse("success", "You have successfully booked the flight!");

        return apiResponse;
    }
}
