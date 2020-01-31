/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary_springboot.test_task.dao;

import java.util.List;

import itinerary_springboot.test_task.model.Itinerary;

public interface ItineraryDao {
	
	List<Itinerary> getAvailableItineraries();

}

