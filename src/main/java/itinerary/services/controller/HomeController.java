/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/itinerary-springboot
 */
package itinerary.services.controller;

import itinerary.services.service.ItineraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for handling requests to the home page of the itinerary application.
 * <p>
 * The main purpose of this controller is to display the list of available flights
 * and any other relevant information on the application's main page.
 * </p>
 */
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private final ItineraryService itineraryService;

    public HomeController(final ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    /**
     * Handles HTTP GET requests to the root path ("/").
     * <p>
     * This method retrieves the list of available flights from the {@link ItineraryService}
     * and adds it to the provided {@link Model} under the attribute name "flights".
     * </p>
     *
     * @param model the {@link Model} object used to pass attributes to the view
     * @return the name of the view to render, in this case "index"
     */
    @GetMapping("/")
    public String home(Model model) {
        LOGGER.info("HomeController in / route");
        // list of available flights
        model.addAttribute("flights", itineraryService.getAvailableFlights());
        return "index"; // view
    }

}
