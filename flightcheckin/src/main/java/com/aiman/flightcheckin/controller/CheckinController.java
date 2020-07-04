package com.aiman.flightcheckin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aiman.flightcheckin.integration.ReservationRestClient;
import com.aiman.flightcheckin.integration.dto.Reservation;
import com.aiman.flightcheckin.integration.dto.ReservationUpdateRequest;

@Controller
public class CheckinController {
	
	@Autowired
	ReservationRestClient reservationRestClient;
	
	@RequestMapping("/showStartCheckin")
	public String showCheckin() {
		
		return "startCheckIn";	
	}
	
	@RequestMapping("/startCheckIn")
	public String startCheckin(@RequestParam("reservationId") long reservationId, ModelMap modelMap) {
		
		Reservation reservation = reservationRestClient.findReservation(reservationId);
		modelMap.addAttribute("reservation", reservation);
		return "displayReservationDetails";
	}
	
	@RequestMapping("completeCheckin")
	public String completeCheckin(@RequestParam("reservationId") long reservationId, 
			@RequestParam("numberOfBags")int numberOfBags) {
		ReservationUpdateRequest reservationUpdateRequest=new ReservationUpdateRequest();
		reservationUpdateRequest.setId(reservationId);
		reservationUpdateRequest.setNumberOfBags(numberOfBags);
		reservationUpdateRequest.setCheckedIn(true);
		reservationRestClient.updateReservation(reservationUpdateRequest);
		return "checkInConfirmation";
	}

}
