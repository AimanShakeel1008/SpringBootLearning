package com.aiman.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiman.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
