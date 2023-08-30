package com.uguz.flightsearch.repository;

import com.uguz.flightsearch.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {
}
