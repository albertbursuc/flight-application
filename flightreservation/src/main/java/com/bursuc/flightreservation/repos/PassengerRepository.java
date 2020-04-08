package com.bursuc.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bursuc.flightreservation.entities.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}
