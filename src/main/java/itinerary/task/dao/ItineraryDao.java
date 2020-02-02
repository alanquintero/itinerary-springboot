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
	 * getAvailableFlights abstract method
	 * 
	 * @return List of Flight
	 */
	List<Flight> getAvailableFlights();

	/**
	 * reserveFlight abstract method
	 * 
	 * @param Reservation
	 * @return Response
	 */
	Response reserveFlight(Reservation reservation);

}
