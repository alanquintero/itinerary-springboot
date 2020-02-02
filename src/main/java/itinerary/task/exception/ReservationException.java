/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.exception;

@SuppressWarnings("serial")
public class ReservationException extends Exception {

	public ReservationException(String errorMessage) {
		super(errorMessage);
	}

}
