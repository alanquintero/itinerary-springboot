/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import itinerary.task.service.ItineraryService;

@Controller
public class MainController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private ItineraryService itineraryService;

	/**
	 * This method is called when access the main page. Returns the user name and
	 * the list of available flights.
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/")
	public String main(Model model) {
		LOGGER.info("MainController in / route");
		// list of available flights
		model.addAttribute("flights", itineraryService.getAvailableFlights());
		return "index"; // view
	}

}
