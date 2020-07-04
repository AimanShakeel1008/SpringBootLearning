package com.aiman.flightreservation.services;

import com.aiman.flightreservation.dto.ReservationRequest;
import com.aiman.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
}
