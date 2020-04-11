package com.bursuc.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bursuc.flightreservation.dto.ReservationUpdateRequest;
import com.bursuc.flightreservation.entities.Reservation;
import com.bursuc.flightreservation.repos.ReservationRepository;
import com.bursuc.flightreservation.util.EmailUtil;

@RestController
@CrossOrigin
public class ReservationRESTController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRESTController.class);

	@Autowired
	ReservationRepository reservationRepository;
	
	@GetMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable Long id) {
		
		LOGGER.info("inside findReservation for id: " + id);
		Reservation reservation = reservationRepository.findById(id).get();
		return reservation;
	}
	
	@PostMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		
		LOGGER.info("inside updateReservation for id: " + request);
		
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setCheckedIn(true);
		reservation.setNumberOfBags(request.getNumberOfBags());
		
		LOGGER.info("Saving reservation");
		Reservation updatedReservation = reservationRepository.save(reservation);
		
		return updatedReservation;
		
	}

}
