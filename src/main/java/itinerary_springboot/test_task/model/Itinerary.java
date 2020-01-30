/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary_springboot.test_task.model;

import itinerary_springboot.test_task.utils.Common;
import itinerary_springboot.test_task.utils.Common.Currency;

public class Itinerary {

	private int id;

	private String origin;

	private String destination;

	private double price;

	private Common.Currency currency;

	private double exchangeRate;

	public Itinerary(int id, String origin, String destination, double price, Currency currency) {
		super();
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.price = price;
		this.currency = currency;
	}

	public Itinerary(int id, String origin, String destination, double price, Currency currency, double exchangeRate) {
		super();
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.price = price;
		this.currency = currency;
		this.exchangeRate = exchangeRate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Common.Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Common.Currency currency) {
		this.currency = currency;
	}

	public double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

}
