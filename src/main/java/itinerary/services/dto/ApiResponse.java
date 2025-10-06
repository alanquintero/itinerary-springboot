/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/itinerary-springboot
 */
package itinerary.services.dto;

/**
 * Represents a standard response for API operations.
 */
public class ApiResponse {

    private String status;

    private String message;

    public ApiResponse() {
        // default values
        status = "error";
        message = "Something went wrong. Please contact admin.";
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
