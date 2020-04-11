package com.bursuc.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bursuc.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
