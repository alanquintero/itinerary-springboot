/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary_springboot.test_task.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import itinerary_springboot.test_task.db.DataBaseConnection;
import itinerary_springboot.test_task.mapper.ItineraryMapper;
import itinerary_springboot.test_task.model.Itinerary;

@Repository
public class ItineraryDaoImpl implements ItineraryDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryDaoImpl.class);
	
	@Autowired
	private DataBaseConnection dbConnection;

	@Override
	public List<Itinerary> getAvailableItineraries() {
		LOGGER.info("ItineraryDao::getAvailableItineraries");
		List<Itinerary> itineraries =  new ArrayList<>();
		Statement stmt = null;
		try {
			stmt = dbConnection.createConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT it.id as id, city_ori.code as origin, city_dst.code as destination, it.price as price, curr.currency as currency, curr.exchange_rate_usd as exchangeRate\r\n" + 
					"FROM itinerary it\r\n" + 
					"JOIN city city_ori ON it.origin_fk = city_ori.id\r\n" + 
					"JOIN city city_dst ON it.destination_fk = city_dst.id\r\n" + 
					"JOIN currency curr ON it.currency_fk = curr.id\r\n" + 
					"WHERE it.book = false");
			itineraries = new ItineraryMapper().mapRow(rs, 0);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			}
			dbConnection.closeConnection();
		}
		return itineraries;
	}

}

