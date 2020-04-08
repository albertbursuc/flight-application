package com.bursuc.flightreservation.services;

import com.bursuc.flightreservation.dto.ReservationRequest;
import com.bursuc.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
}
