/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.service;

import java.util.List;

import itinerary.task.model.Flight;
import itinerary.task.model.Reservation;
import itinerary.task.model.Response;

public interface ItineraryService {

	/**
	 * getAvailableItineraries abstract method
	 * @return List of Itinerary
	 */
	List<Flight> getAvailableItineraries();
	
	/**
	 * reserveFlight abstract method
	 * @param reservation
	 * @return Response
	 */
	Response reserveFlight(Reservation reservation);

}
