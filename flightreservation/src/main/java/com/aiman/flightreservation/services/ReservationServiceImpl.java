/**
 * 
 */
package com.aiman.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiman.flightreservation.dto.ReservationRequest;
import com.aiman.flightreservation.entities.Flight;
import com.aiman.flightreservation.entities.Passenger;
import com.aiman.flightreservation.entities.Reservation;
import com.aiman.flightreservation.repos.FlightRepository;
import com.aiman.flightreservation.repos.PassengerRepository;
import com.aiman.flightreservation.repos.ReservationRepository;
import com.aiman.flightreservation.util.EmailUtil;
import com.aiman.flightreservation.util.PDFGenerator;

/**
 * @author Aiman Shakeel
 *
 */

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Value("${com.aiman.flightreservation.itinerary.directorypath}")
	private String ITINERARY_DIR;

	private  static final Logger LOGGER=LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		
		LOGGER.info("Inside bookFlight()");
		
		long flightId = request.getFlightId();
		
		LOGGER.info("fetching flight for: "+flightId);
		
		Flight flight = flightRepository.findById(flightId).get();
		
		
		Passenger passenger=new Passenger();
		
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		
		LOGGER.info("saving passenger:"+passenger);
		
		Passenger savedpassenger = passengerRepository.save(passenger);
		
		Reservation reservation= new Reservation();
		
		reservation.setFlight(flight);
		reservation.setPassenger(savedpassenger);
		reservation.setCheckedIn(false);
		
		LOGGER.info("saving reservation:"+reservation);
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filePath=ITINERARY_DIR+savedReservation.getId()+".pdf";
		
		LOGGER.info("generating Itinerary:");
		
		pdfGenerator.generateItinerary(savedReservation, filePath);
		
		LOGGER.info("sending Itinerary:");
		
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		
		return savedReservation;
	}

}
