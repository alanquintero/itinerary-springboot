/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import itinerary.task.dao.ItineraryDao;
import itinerary.task.model.Flight;
import itinerary.task.model.Reservation;
import itinerary.task.model.Response;
import itinerary.task.utils.Constants;

@SpringBootTest
public class ItineraryServiceImplTest {

	@Mock
	private ItineraryDao itineraryDao;

	@InjectMocks
	private ItineraryService itineraryService = new ItineraryServiceImpl();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetAvailableFlights() {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight(1, "SFO", "GDL", 200d, "USD", 1));
		flights.add(new Flight(2, "GDL", "SFO", 6000d, "MXN", 19.10));

		Mockito.when(itineraryDao.getAvailableFlights()).thenReturn(flights);

		List<Flight> availableFlights = itineraryService.getAvailableFlights();

		assertNotNull(availableFlights);
		assertFalse(availableFlights.isEmpty());
		assertEquals(availableFlights.get(0).getDestination(), flights.get(0).getDestination());
	}

	@Test
	void testNoAvailableFlightsFound() {
		Mockito.when(itineraryDao.getAvailableFlights()).thenReturn(new ArrayList<Flight>());

		List<Flight> availableFlights = itineraryService.getAvailableFlights();

		assertNotNull(availableFlights);
		assertTrue(availableFlights.isEmpty());
	}

	@Test
	void testReserveFlight() {
		Response mockResponse = new Response();
		mockResponse.setResponse(Constants.STATUS_SUCCESS, Constants.MESSAGE_SUCCESS);
		Mockito.when(itineraryDao.reserveFlight(Mockito.any(Reservation.class))).thenReturn(mockResponse);

		Reservation reservation = new Reservation();
		reservation.setFlightId(1);
		reservation.setFirstName("Alan");
		reservation.setLastName("Quintero");

		Response actualResponse = itineraryService.reserveFlight(reservation);

		assertEquals(mockResponse.getStatus(), actualResponse.getStatus());
		assertEquals(mockResponse.getMessage(), actualResponse.getMessage());
	}

}
