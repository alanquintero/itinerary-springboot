/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import itinerary.task.db.DataBaseConnection;
import itinerary.task.mapper.FlightMapper;
import itinerary.task.model.Flight;
import itinerary.task.model.Reservation;
import itinerary.task.model.Response;
import itinerary.task.utils.Constants;

@Repository
public class ItineraryDaoImpl implements ItineraryDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryDaoImpl.class);

	@Autowired
	private DataBaseConnection dbConnection;

	@Override
	public List<Flight> getAvailableItineraries() {
		LOGGER.info("ItineraryDao::getAvailableItineraries");
		List<Flight> itineraries = new ArrayList<>();
		Statement stmt = null;
		try {
			stmt = dbConnection.createConnection().createStatement();
			ResultSet rs = stmt.executeQuery(Constants.QUERY_AVAILABLE_FLIGHTS);
			itineraries = new FlightMapper().mapRow(rs, 0);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				LOGGER.error(e.getMessage(), e);
			}
			dbConnection.closeConnection();
		}
		return itineraries;
	}

	@Override
	public Response reserveFlight(Reservation reservation) {
		LOGGER.info("ItineraryDao::reserveFlight for flight id: {}", reservation.getFlightId());
		Response response = new Response();
		boolean isFlightBooked = true;

		// Check is Flight is Booked
		isFlightBooked = isFlightAlreadyBooked(reservation.getFlightId());

		// Book a Flight if flight is not booked yet
		response = bookFlight(reservation, isFlightBooked);

		// Update list of available flights
		if (!isFlightBooked && response.getStatus() == Constants.STATUS_SUCCESS) {
			updateBookedFlight(reservation.getFlightId());
		}

		return response;
	}

	private boolean isFlightAlreadyBooked(int flightId) {
		boolean isFlightBooked = false;
		try (PreparedStatement preparedStatement = dbConnection.createConnection()
				.prepareStatement(Constants.QUERY_IS_FLIGHT_AVAILABLE);) {
			preparedStatement.setInt(1, flightId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isFlightBooked = resultSet.getBoolean(Constants.FIELD_IS_FLIGHT_BOOK);
			}
			LOGGER.info("The Flight {} is Available? : {}", flightId, isFlightBooked);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			dbConnection.closeConnection();
		}
		return isFlightBooked;
	}

	private Response bookFlight(Reservation reservation, boolean isFlightBooked) {
		Response response = new Response();
		try (PreparedStatement preparedStatement = dbConnection.createConnection()
				.prepareStatement(Constants.QUERY_BOOK_FLIGHT);) {
			if (isFlightBooked) {
				LOGGER.info("The Flight {} is not available", reservation.getFlightId());
				// flight is not available
				response.setResponse(Constants.STATUS_ALREADY_BOOKED, Constants.MESSAGE_ALREADY_BOOKED);
			} else {
				LOGGER.info("The Flight {} is Available", reservation.getFlightId());
				// flight is available
				preparedStatement.setInt(1, reservation.getFlightId());
				preparedStatement.setString(2, reservation.getFirstName());
				preparedStatement.setString(3, reservation.getLastName());
				// return the number of rows affected in db.
				// if insert was successful must return 1.
				int isBook = preparedStatement.executeUpdate();
				if (isBook == 1) {
					LOGGER.info("The Flight {} was Booked", reservation.getFlightId());
					// The flight was booked
					response.setResponse(Constants.STATUS_SUCCESS, Constants.MESSAGE_SUCCESS);
				} else {
					LOGGER.info("The Flight {} was already booked for another user", reservation.getFlightId());
					// The flight was NOT booked because another user already did it
					response.setResponse(Constants.STATUS_ALREADY_BOOKED, Constants.MESSAGE_ALREADY_BOOKED);
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			dbConnection.closeConnection();
		}
		return response;
	}

	private void updateBookedFlight(int flightId) {
		try (PreparedStatement preparedStatement = dbConnection.createConnection()
				.prepareStatement(Constants.QUERY_UPDATE_BOOKED_FLIGHT);) {
			LOGGER.info("Set Flight {} as Booked", flightId);
			preparedStatement.setInt(1, flightId);
			// return the number of rows affected in db.
			// if update was successful must return 1.
			int isBook = preparedStatement.executeUpdate();
			if (isBook != 1) {
				LOGGER.error("There was an ERROR during the update of a booked flight {}", flightId);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			dbConnection.closeConnection();
		}
	}

}
