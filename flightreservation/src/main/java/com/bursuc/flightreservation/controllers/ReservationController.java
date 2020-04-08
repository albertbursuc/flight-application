package com.bursuc.flightreservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bursuc.flightreservation.dto.ReservationRequest;
import com.bursuc.flightreservation.entities.Flight;
import com.bursuc.flightreservation.entities.Reservation;
import com.bursuc.flightreservation.repos.FlightRepository;
import com.bursuc.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	FlightRepository flightRepository;
	
	@GetMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam Long flightId, Model model) {
		
		Flight flight = flightRepository.findById(flightId).get();
		model.addAttribute("flight", flight);
		
		return "completeReservation";
	}

	@PostMapping("/completeReservation")
	public String completeReservation(@ModelAttribute ReservationRequest request, Model model) {
		
		Reservation reservation = reservationService.bookFlight(request);
		Long id = reservation.getId();
		model.addAttribute("msg", "Reservation created successfully and the id is " + reservation.getId())	;
		
		return "reservationConfirmation";
		
	}
}
