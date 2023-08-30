package com.uguz.flightsearch.repository;

import com.uguz.flightsearch.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
