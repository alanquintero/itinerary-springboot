/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/itinerary-springboot
 */
package itinerary.services.service;

import itinerary.services.model.Flight;
import itinerary.services.model.Reservation;
import itinerary.services.repository.FlightRepository;
import itinerary.services.repository.ReservationRepository;
import itinerary.services.dto.ApiResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItineraryServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private ReservationRepository reservationRepository;

    private AutoCloseable mocks;

    @InjectMocks
    private ItineraryService itineraryService;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void reserveFlight_success() {
        // Arrange
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setBook(false);

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        ApiResponse response = itineraryService.reserveFlight(1L, "Alan", "Quintero");

        // Assert
        assertEquals("success", response.getStatus());
        assertEquals("You have successfully booked the flight!", response.getMessage());
        assertTrue(flight.isBook());

        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verify(flightRepository, times(1)).save(flight);
    }

    @Test
    void reserveFlight_alreadyBooked() {
        // Arrange
        Flight flight = new Flight();
        flight.setId(2L);
        flight.setBook(true);

        when(flightRepository.findById(2L)).thenReturn(Optional.of(flight));

        // Act
        ApiResponse response = itineraryService.reserveFlight(2L, "Alan", "Quintero");

        // Assert
        assertEquals("already-book", response.getStatus());
        assertTrue(flight.isBook());

        verify(reservationRepository, never()).save(any(Reservation.class));
        verify(flightRepository, never()).save(any(Flight.class));
    }

    @Test
    void reserveFlight_flightNotFound() {
        // Arrange
        when(flightRepository.findById(3L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                itineraryService.reserveFlight(3L, "Alan", "Quintero"));

        assertEquals("Flight not found with id 3", exception.getMessage());
    }
}

