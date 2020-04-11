package com.bursuc.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bursuc.flightreservation.controllers.FlightController;
import com.bursuc.flightreservation.dto.ReservationRequest;
import com.bursuc.flightreservation.entities.Flight;
import com.bursuc.flightreservation.entities.Passenger;
import com.bursuc.flightreservation.entities.Reservation;
import com.bursuc.flightreservation.repos.FlightRepository;
import com.bursuc.flightreservation.repos.PassengerRepository;
import com.bursuc.flightreservation.repos.ReservationRepository;
import com.bursuc.flightreservation.util.EmailUtil;
import com.bursuc.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.bursuc.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;


	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationRepository reservationRepository;

	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		LOGGER.info("inside bookFlight()");
		//Make payment
		
		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).get();
		
		LOGGER.info("Fetching flight for flight id: " + flightId );
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		
		LOGGER.info("Saving passenger " + passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		LOGGER.info("Saving reservation " + reservation);
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
		
		LOGGER.info("Generating the itinerary...");
		
		pdfGenerator.generateItinerary(savedReservation, filePath);
		
		LOGGER.info("Sending the itinerary to email...");
		
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		return savedReservation;
	}

}
