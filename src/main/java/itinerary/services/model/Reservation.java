/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/itinerary-springboot
 */
package itinerary.services.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * This entity is mapped to the {@code reservation} table in the database.
 * Each reservation is associated with a specific flight and contains the
 * passenger's first name, last name, and the date the reservation was made.
 */
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "flight_fk")
    private Long flightFk;

    @OneToOne
    @MapsId // flight_fk is both PK and FK
    @JoinColumn(name = "flight_fk")
    private Flight flight;

    @Column(name = "firstname", length = 30, nullable = false)
    private String firstname;

    @Column(name = "lastname", length = 30, nullable = false)
    private String lastname;

    @Column(name = "date_booked", nullable = false)
    private LocalDateTime dateBooked;

    public Long getFlightFk() {
        return flightFk;
    }

    public void setFlightFk(Long flightFk) {
        this.flightFk = flightFk;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDateTime getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(LocalDateTime dateBooked) {
        this.dateBooked = dateBooked;
    }
}
