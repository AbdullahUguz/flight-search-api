package com.uguz.flightsearch.business.service;

import com.uguz.flightsearch.dto.FlightDto;
import com.uguz.flightsearch.entity.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    Flight create(FlightDto flightDto);
    List<Flight> getAll();
    Flight update(long flightId, FlightDto flightDto);
    void delete(long flightId);
    List<Flight> findFlight(String departureAirport, String arrivalAirport, LocalDate departureDate,LocalDate returnDate);
}
