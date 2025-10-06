/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/itinerary-springboot
 */
package itinerary.services.repository;

import itinerary.services.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing reservation data from the database.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
