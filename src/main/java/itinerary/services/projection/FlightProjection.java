/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/itinerary-springboot
 */
package itinerary.services.projection;

/**
 * Projection interface for retrieving flight data with related city and currency details.
 * <p>
 * This interface is used in Spring Data JPA to fetch only specific columns from the database
 * and avoid loading full entities when not necessary.
 * </p>
 */
public interface FlightProjection {
    Long getId();

    String getOrigin();

    String getDestination();

    Double getPrice();

    String getCurrency();

    Double getPriceUsd();
}
