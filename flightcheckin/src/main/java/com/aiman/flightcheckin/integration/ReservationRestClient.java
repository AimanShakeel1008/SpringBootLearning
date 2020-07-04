package com.aiman.flightcheckin.integration;

import com.aiman.flightcheckin.integration.dto.Reservation;
import com.aiman.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {
	
	public Reservation findReservation(long id);
	
	public Reservation updateReservation(ReservationUpdateRequest request);

}
