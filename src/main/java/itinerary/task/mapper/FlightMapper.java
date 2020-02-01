/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import itinerary.task.model.Flight;
import itinerary.task.utils.Constants;

public class FlightMapper implements RowMapper<List<Flight>> {

	@Override
	public List<Flight> mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<Flight> itineraries = new ArrayList<>();
		while (rs.next()) {
			Flight state = new Flight(rs.getInt(Constants.FIELD_FLIGHT_ID), rs.getString(Constants.FIELD_FLIGHT_ORIGIN),
					rs.getString(Constants.FIELD_FLIGHT_DESTINATION), rs.getDouble(Constants.FIELD_FLIGHT_PRICE),
					rs.getString(Constants.FIELD_FLIGHT_CURRENCY), rs.getDouble(Constants.FIELD_FLIGHT_EXCHANGE_RATE));
			itineraries.add(state);
			state = null;
		}
		return itineraries;
	}

}
