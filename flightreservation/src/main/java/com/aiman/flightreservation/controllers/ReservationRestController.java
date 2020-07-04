package com.aiman.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiman.flightreservation.dto.ReservationUpdateRequest;
import com.aiman.flightreservation.entities.Reservation;
import com.aiman.flightreservation.repos.ReservationRepository;


@RestController
@CrossOrigin
public class ReservationRestController {
	
	private  static final Logger LOGGER=LoggerFactory.getLogger(ReservationRestController.class);

	@Autowired
	ReservationRepository reservationRepository;

	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") long id) {
		
		LOGGER.info("inside findReservation() "+id);

		return reservationRepository.findById(id).get();

	}

	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		
		LOGGER.info("inside updateReservation() for  "+request);

		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setCheckedIn(request.getCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		
		LOGGER.info("saving reservation");

		Reservation updatedReservation = reservationRepository.save(reservation);

		return updatedReservation;

	}
}
