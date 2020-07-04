package com.aiman.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiman.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
