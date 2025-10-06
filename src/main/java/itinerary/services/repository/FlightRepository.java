/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/itinerary-springboot
 */
package itinerary.services.repository;

import itinerary.services.model.Flight;
import itinerary.services.projection.FlightProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing flight data from the database.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @SuppressWarnings("SqlNoDataSourceInspection")
    @Query(
            value = """
                    SELECT fl.id AS id,
                           city_ori.code AS origin,
                           city_dst.code AS destination,
                           fl.price AS price,
                           curr.currency AS currency,
                           ROUND(fl.price /  curr.exchange_rate_usd, 2) AS priceUsd
                    FROM flight fl
                    JOIN city city_ori ON fl.origin_fk = city_ori.id
                    JOIN city city_dst ON fl.destination_fk = city_dst.id
                    JOIN currency curr ON fl.currency_fk = curr.id
                    WHERE fl.book = false
                    """,
            nativeQuery = true
    )
    List<FlightProjection> findAvailableFlights();
}
