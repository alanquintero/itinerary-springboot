/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary_springboot.test_task.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itinerary_springboot.test_task.dao.ItineraryDao;
import itinerary_springboot.test_task.model.Itinerary;

@Service
public class ItineraryServiceImpl implements ItineraryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryServiceImpl.class);

	@Autowired
	private ItineraryDao itineraryDao;

	public List<Itinerary> getAvailableItineraries() {
		LOGGER.info("ItineraryService::getAvailableItineraries");
		return itineraryDao.getAvailableItineraries();
	}

}
