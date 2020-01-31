/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary_springboot.test_task.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import itinerary_springboot.test_task.model.Itinerary;

public class ItineraryMapper implements RowMapper<List<Itinerary>> {

	@Override
	public List<Itinerary> mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<Itinerary> itineraries = new ArrayList<>();
		while (rs.next()) {
			Itinerary state = new Itinerary(rs.getInt("id"), rs.getString("origin"), rs.getString("destination"),
					rs.getDouble("price"), rs.getString("currency"), rs.getDouble("exchangeRate"));
			itineraries.add(state);
			state = null;
		}
		return itineraries;
	}

}
