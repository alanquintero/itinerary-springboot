/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.utils;

public class Constants {

	/* QUERIES */
	public static final String QUERY_AVAILABLE_FLIGHTS = "SELECT fl.id as id, city_ori.code as origin, city_dst.code as destination, fl.price as price, curr.currency as currency, curr.exchange_rate_usd as exchangeRate "
			+ "FROM flights fl " + "JOIN city city_ori ON fl.origin_fk = city_ori.id "
			+ "JOIN city city_dst ON fl.destination_fk = city_dst.id "
			+ "JOIN currency curr ON fl.currency_fk = curr.id " + "WHERE fl.book = false";

	public static final String QUERY_IS_FLIGHT_AVAILABLE = "SELECT book FROM flights WHERE id = ?";

	public static final String QUERY_BOOK_FLIGHT = "INSERT INTO reservation(flight_fk, firstname, lastname, date_booked) values (?, ?, ?, sysdate())";

	public static final String QUERY_UPDATE_BOOKED_FLIGHT = "UPDATE flights SET book = true WHERE id = ?";

	/* DB FIELDS */
	public static final String FIELD_IS_FLIGHT_BOOK = "book";

	public static final String FIELD_FLIGHT_ID = "id";

	public static final String FIELD_FLIGHT_ORIGIN = "origin";

	public static final String FIELD_FLIGHT_DESTINATION = "destination";

	public static final String FIELD_FLIGHT_PRICE = "price";

	public static final String FIELD_FLIGHT_CURRENCY = "currency";

	public static final String FIELD_FLIGHT_EXCHANGE_RATE = "exchangeRate";

	/* STATUS */
	public static final String STATUS_SUCCESS = "success";

	public static final String MESSAGE_SUCCESS = "You have successfully booked the flight!";

	public static final String STATUS_ALREADY_BOOKED = "already-book";

	public static final String MESSAGE_ALREADY_BOOKED = "We're sorry! The flight has already been reserved by a faster user...";

}
