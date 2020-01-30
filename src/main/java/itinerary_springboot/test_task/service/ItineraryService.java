/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary_springboot.test_task.service;

import java.util.List;

import itinerary_springboot.test_task.model.Itinerary;

public interface ItineraryService {

	List<Itinerary> getAvailableItineraries();

}
