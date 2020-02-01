/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.model;

public class Flight {

	private int id;

	private String origin;

	private String destination;

	private double price;

	private String currency;

	private double exchangeRate;

	public Flight(int id, String origin, String destination, double price, String currency) {
		super();
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.price = price;
		this.currency = currency;
	}

	public Flight(int id, String origin, String destination, double price, String currency, double exchangeRate) {
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

}
