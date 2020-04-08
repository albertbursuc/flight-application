package com.bursuc.location.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bursuc.location.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

	

}
