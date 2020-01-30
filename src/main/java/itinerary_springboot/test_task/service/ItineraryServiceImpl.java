/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary_springboot.test_task.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import itinerary_springboot.test_task.model.Itinerary;
import itinerary_springboot.test_task.utils.Common;

@Service
public class ItineraryServiceImpl implements ItineraryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryServiceImpl.class);

	public List<Itinerary> getAvailableItineraries() {
		LOGGER.info("ItineraryService::getAvailableItineraries");
		// TODO remove this hardcoded data, need to call DB
		List<Itinerary> itineraries = new ArrayList<>();
		Itinerary it1 = new Itinerary(1, "SFO", "DME", 1000, Common.Currency.USD);
		Itinerary it2 = new Itinerary(1, "DME", "LHR", 100, Common.Currency.EUR);
		itineraries.add(it1);
		itineraries.add(it2);
		return itineraries;
	}

}
