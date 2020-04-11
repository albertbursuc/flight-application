package com.bursuc.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bursuc.flightreservation.entities.Flight;
import com.bursuc.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

	
	@Autowired
	FlightRepository flightRepository;

	@PostMapping("/findFlights")
	public String findFlights(@RequestParam String departureCity, @RequestParam String arrivalCity,
			@RequestParam @DateTimeFormat(iso = ISO.DATE) Date dateOfDeparture, Model model) {

		LOGGER.info("inside findFlights() from " + departureCity + " to " + arrivalCity + " on " + dateOfDeparture);
		
		List<Flight> flights = flightRepository.findFlights(departureCity, arrivalCity, dateOfDeparture);
		model.addAttribute("flights", flights);
		
		LOGGER.info("Flights found are: " + flights);
		return "displayFlights";
	}
	
	@RequestMapping("/admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
	}
}
