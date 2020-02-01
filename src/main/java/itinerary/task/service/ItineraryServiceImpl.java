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

import itinerary.task.dao.ItineraryDao;
import itinerary.task.model.Flight;
import itinerary.task.model.Reservation;
import itinerary.task.model.Response;

@Service
public class ItineraryServiceImpl implements ItineraryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryServiceImpl.class);

	@Autowired
	private ItineraryDao itineraryDao;

	public List<Flight> getAvailableItineraries() {
		LOGGER.info("ItineraryService::getAvailableItineraries");
		// TODO add exchange rate
		return itineraryDao.getAvailableItineraries();
	}

	@Override
	public Response reserveFlight(Reservation reservation) {
		return itineraryDao.reserveFlight(reservation);
	}

}
