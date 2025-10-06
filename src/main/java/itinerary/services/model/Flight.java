/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/itinerary-springboot
 */
package itinerary.services.model;

import jakarta.persistence.*;

/**
 * This entity is mapped to the {@code flight} table in the database.
 * Each flight has an origin city, a destination city, a price, currency, and a booking status.
 */
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_fk", nullable = false)
    private City origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_fk", nullable = false)
    private City destination;

    @Column(nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_fk", nullable = false)
    private Currency currency;

    @Transient
    private double priceUsd;

    @Column(nullable = false)
    private boolean book;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(final City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(final City destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public boolean isBook() {
        return book;
    }

    public void setBook(final boolean book) {
        this.book = book;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(final double priceUsd) {
        this.priceUsd = priceUsd;
    }
}
