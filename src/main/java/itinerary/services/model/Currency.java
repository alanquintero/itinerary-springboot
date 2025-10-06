/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/itinerary-springboot
 */
package itinerary.services.model;

import jakarta.persistence.*;

/**
 * This entity is mapped to the {@code currency} table in the database.
 * It stores the currency code and the exchange rate to USD.
 */
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;

    private double exchangeRateUsd;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public double getExchangeRateUsd() {
        return exchangeRateUsd;
    }

    public void setExchangeRateUsd(final double exchangeRateUsd) {
        this.exchangeRateUsd = exchangeRateUsd;
    }
}
