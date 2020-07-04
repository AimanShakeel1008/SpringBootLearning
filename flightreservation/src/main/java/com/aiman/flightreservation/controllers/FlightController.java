package com.aiman.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aiman.flightreservation.entities.Flight;
import com.aiman.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {
	
	@Autowired
	FlightRepository flightRepository;
	
	private  static final Logger LOGGER=LoggerFactory.getLogger(FlightController.class);
	
    @RequestMapping("/findFlights")
	public String findFlights(@RequestParam("from")String from, 
			                  @RequestParam("to")String to,
			                  @RequestParam("dateOfDeparture") @DateTimeFormat(pattern="MM-dd-yyyy") Date dateOfDeparture,
			                  ModelMap modelMap) {
		
    	LOGGER.info("inside findFlights() From: "+from+" TO: "+to+" Departure Date:"+dateOfDeparture);
    	
    	List<Flight> flights=flightRepository.findFlights(from, to ,dateOfDeparture);
    	modelMap.addAttribute("flights", flights);
    	
    	LOGGER.info("Flights found are:"+flights);
		
		return "displayFlights";
	}
    
    
    @RequestMapping("admin/showAddFlight")
    public String showAddFlight() {
    	
    	return "addFlight";
    }
}
