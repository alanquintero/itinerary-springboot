/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.dao;

import java.util.List;

import itinerary.task.model.Flight;
import itinerary.task.model.Reservation;
import itinerary.task.model.Response;

public interface ItineraryDao {
	
	/**
	 * getAvailableItineraries abstract method
	 * @return List of Itinerary
	 */
	List<Flight> getAvailableItineraries();
	
	/**
	 * reserveFlight abstract method
	 * @param reservation
	 * @return Reservation
	 */
	Response reserveFlight(Reservation reservation);

}

