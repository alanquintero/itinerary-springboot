/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import itinerary.task.exception.ReservationException;
import itinerary.task.model.Reservation;
import itinerary.task.model.Response;
import itinerary.task.utils.Constants;

@SpringBootTest
public class ItineraryDaoImplTest {

	@InjectMocks
	private ItineraryDaoImpl itineraryDao;

	@Test
	public void testSuccessReserveFight() throws ReservationException {
		Response mockResponse = new Response();
		mockResponse.setResponse(Constants.STATUS_SUCCESS, Constants.MESSAGE_SUCCESS);
		itineraryDao = Mockito.mock(ItineraryDaoImpl.class);
		ItineraryDaoImpl spy = Mockito.spy(itineraryDao);
		// Mocking all the call to the DB
		Mockito.when(itineraryDao.reserveFlight(Mockito.any(Reservation.class))).thenCallRealMethod();
		Mockito.when(itineraryDao.isFlightAlreadyBooked(Mockito.anyInt())).thenReturn(true);
		Mockito.when(itineraryDao.bookFlight(Mockito.any(Reservation.class), Mockito.anyBoolean()))
				.thenReturn(mockResponse);
		Mockito.doNothing().when(spy).updateBookedFlight(Mockito.anyInt());

		Reservation reservation = new Reservation();
		reservation.setFlightId(1);
		reservation.setFirstName("Alan");
		reservation.setLastName("Quintero");

		Response response = itineraryDao.reserveFlight(reservation);

		assertEquals(Constants.STATUS_SUCCESS, response.getStatus());
		assertEquals(Constants.MESSAGE_SUCCESS, response.getMessage());
	}

}
