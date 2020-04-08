package com.bursuc.flightcheckin.integration;

import com.bursuc.flightcheckin.integration.dto.Reservation;
import com.bursuc.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {
	
	public Reservation findReservation(Long id);
	public Reservation updateReservation(ReservationUpdateRequest request);

}
