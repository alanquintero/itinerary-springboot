/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import itinerary.task.exception.ReservationException;
import itinerary.task.mapper.FlightMapper;
import itinerary.task.model.Flight;
import itinerary.task.model.Reservation;
import itinerary.task.model.Response;
import itinerary.task.utils.Constants;

@Repository
public class ItineraryDaoImpl extends JdbcDaoSupport implements ItineraryDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryDaoImpl.class);

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	/**
	 * Get the list of available flights.
	 * 
	 * @return List of Flight
	 */
	@Override
	public List<Flight> getAvailableFlights() {
		LOGGER.info("ItineraryDao::getAvailableFlights");
		// List will be empty if no results
		List<Flight> itineraries = new ArrayList<>();
		try (Statement stmt = getJdbcTemplate().getDataSource().getConnection().createStatement();) {
			ResultSet rs = stmt.executeQuery(Constants.QUERY_AVAILABLE_FLIGHTS);
			// using a mapper to parse the list of results
			itineraries = new FlightMapper().mapRow(rs, 0);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return itineraries;
	}

	/**
	 * Creates a reservation in the db and update the flight as not available.
	 * 
	 * @param reservation
	 * @return Reservation
	 */
	@Override
	public Response reserveFlight(Reservation reservation) {
		LOGGER.info("ItineraryDao::reserveFlight for flight id: {}", reservation.getFlightId());
		Response response = new Response();
		boolean isFlightBooked = true;

		try {
			// Check is Flight is Booked
			isFlightBooked = isFlightAlreadyBooked(reservation.getFlightId());

			// Book a Flight if flight is not booked yet
			response = bookFlight(reservation, isFlightBooked);

			/*
			 * NOTE: the below functionality could be improve in a trigger in the db When a
			 * new row is inserted into the reservation table then update book as true When
			 * a row is deleted from the reservation then update book as false
			 */
			// Update list of available flights
			if (!isFlightBooked && response.getStatus() == Constants.STATUS_SUCCESS) {
				updateBookedFlight(reservation.getFlightId());
			}
		} catch (ReservationException e) {
			// Generic message for the user
			response.setResponse(Constants.STATUS_ALREADY_BOOKED, Constants.MESSAGE_ALREADY_BOOKED);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return response;
	}

	/**
	 * Check is flight is still available.
	 * 
	 * @param flightId
	 * @return boolean
	 */
	public boolean isFlightAlreadyBooked(long flightId) throws ReservationException {
		boolean isFlightBooked = false;
		try (PreparedStatement preparedStatement = getJdbcTemplate().getDataSource().getConnection()
				.prepareStatement(Constants.QUERY_IS_FLIGHT_AVAILABLE);) {
			preparedStatement.setLong(1, flightId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isFlightBooked = resultSet.getBoolean(Constants.FIELD_IS_FLIGHT_BOOK);
			} else {
				// no response, flight does not exist
				throw new ReservationException("Flight " + flightId + " is invalid");
			}
			LOGGER.info("is the Flight {} Available? : {}", flightId, isFlightBooked);
		} catch (ReservationException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ReservationException(e.getMessage());
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ReservationException(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ReservationException(e.getMessage());
		}
		return isFlightBooked;
	}

	/**
	 * Creates a reservation for a flight.
	 * 
	 * @param reservation
	 * @param isFlightBooked
	 * @return Response
	 * @throws ReservationException
	 */
	public Response bookFlight(Reservation reservation, boolean isFlightBooked) throws ReservationException {
		Response response = new Response();
		try (PreparedStatement preparedStatement = getJdbcTemplate().getDataSource().getConnection()
				.prepareStatement(Constants.QUERY_BOOK_FLIGHT);) {
			if (isFlightBooked) {
				LOGGER.info("The Flight {} is not available", reservation.getFlightId());
				// flight is not available
				response.setResponse(Constants.STATUS_ALREADY_BOOKED, Constants.MESSAGE_ALREADY_BOOKED);
			} else {
				LOGGER.info("The Flight {} is Available", reservation.getFlightId());
				// flight is available
				preparedStatement.setLong(1, reservation.getFlightId());
				preparedStatement.setString(2, reservation.getFirstName());
				preparedStatement.setString(3, reservation.getLastName());
				// return the number of rows affected in db.
				// if insert was successful must return 1.
				int isBook = preparedStatement.executeUpdate();
				if (isBook == 1) {
					LOGGER.info("The Flight {} reservation was created.", reservation.getFlightId());
					// The flight was booked
					response.setResponse(Constants.STATUS_SUCCESS, Constants.MESSAGE_SUCCESS);
				} else {
					LOGGER.info("The Flight {} was already reserved for another user", reservation.getFlightId());
					// The flight was NOT booked because another user already did it
					response.setResponse(Constants.STATUS_ALREADY_BOOKED, Constants.MESSAGE_ALREADY_BOOKED);
				}
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.info("The Flight {} is not available", reservation.getFlightId());
			response.setResponse(Constants.STATUS_ALREADY_BOOKED, Constants.MESSAGE_ALREADY_BOOKED);
			throw new ReservationException(e.getMessage());
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ReservationException(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ReservationException(e.getMessage());
		}
		return response;
	}

	/**
	 * Mark flight as not available, so it won't appear in the list of available
	 * flights.
	 * 
	 * @param flightId
	 * @throws ReservationException
	 */
	public void updateBookedFlight(long flightId) throws ReservationException {
		try (PreparedStatement preparedStatement = getJdbcTemplate().getDataSource().getConnection()
				.prepareStatement(Constants.QUERY_UPDATE_BOOKED_FLIGHT);) {
			LOGGER.info("Set Flight {} as Not Available", flightId);
			preparedStatement.setLong(1, flightId);
			// return the number of rows affected in db.
			// if update was successful must return 1.
			int isBook = preparedStatement.executeUpdate();
			if (isBook != 1) {
				LOGGER.error("There was an ERROR during the marking the flight {} as Not Available", flightId);
			} else {
				LOGGER.info("The Flight {} was marked as Not Available", flightId);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ReservationException(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ReservationException(e.getMessage());
		}
	}

}
