/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import itinerary.task.dao.ItineraryDao;
import itinerary.task.model.Flight;
import itinerary.task.model.Reservation;
import itinerary.task.model.Response;
import itinerary.task.utils.Common;

@Service
public class ItineraryServiceImpl implements ItineraryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryServiceImpl.class);

	@Autowired
	private ItineraryDao itineraryDao;

	public List<Flight> getAvailableFlights() {
		LOGGER.info("ItineraryService::getAvailableFlights");
		List<Flight> availableFlights = itineraryDao.getAvailableFlights();
		/*
		 * Note: this could be done with a query when getting the list but I wanted to
		 * have some operations with the data in the service layer.
		 */
		availableFlights.stream()
				.forEach(i -> i.setUsdPrice(Common.getDoubleWithFixedDecimals(i.getPrice() / i.getExchangeRate())));
		return availableFlights;
	}

	@Override
	@Transactional
	public Response reserveFlight(Reservation reservation) {
		LOGGER.info("ItineraryService::reserveFlight");
		return itineraryDao.reserveFlight(reservation);
	}

}
