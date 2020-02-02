/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.model;

import itinerary.task.utils.Constants;

public class Response {

	private String status;

	private String message;

	public Response() {
		// default values
		status = Constants.STATUS_ERROR;
		message = Constants.MESSAGE_ERROR;
	}

	public void setResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
