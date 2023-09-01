package com.uguz.flightsearch.repository;

import com.uguz.flightsearch.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findFlightsByDepartureAirport_CityAndArrivalAirport_CityAndDepartureDate(
            String departureCity, String arrivalCity, LocalDate departureDate);

//    List<Flight> findFlightsByDepartureAirport_CityAndArrivalAirport_CityAndReturnDate(
//            String departureCity, String arrivalCity, LocalDate returnDate);
}
