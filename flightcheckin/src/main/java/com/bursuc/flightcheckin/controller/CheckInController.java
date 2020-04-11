package com.bursuc.flightcheckin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bursuc.flightcheckin.integration.ReservationRestClient;
import com.bursuc.flightcheckin.integration.dto.Reservation;
import com.bursuc.flightcheckin.integration.dto.ReservationUpdateRequest;

@Controller
public class CheckInController {
	
	@Autowired
	ReservationRestClient reservationRestClient;
	
	@RequestMapping("/showStartCheckIn")
	public String showStartCheckIn() {
		return "startCheckIn";
	}
	
	@PostMapping("/startCheckIn")
	public String startCheckIn(@RequestParam Long reservationId, Model model) {
		
		Reservation reservation = reservationRestClient.findReservation(reservationId);
		model.addAttribute("reservation", reservation);
		return "displayReservationDetails";
	}
	
	@PostMapping("/completeCheckIn")
	public String completeCheckIn(@RequestParam Long reservationId, @RequestParam int numberOfBags, Model model) {
		
		ReservationUpdateRequest reservationUpdateRequest = new ReservationUpdateRequest();
		reservationUpdateRequest.setId(reservationId);
		reservationUpdateRequest.setCheckIn(true);
		reservationUpdateRequest.setNumberOfBags(numberOfBags);

		Reservation reservation = reservationRestClient.updateReservation(reservationUpdateRequest);
		
		model.addAttribute("reservation", reservation);
		
		return "checkInConfirmation";
		
	}

}
