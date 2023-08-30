package com.uguz.flightsearch.repository;

import com.uguz.flightsearch.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
