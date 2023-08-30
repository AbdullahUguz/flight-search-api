package com.uguz.flightsearch.repository;

import com.uguz.flightsearch.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

//    @Query("SELECT f FROM Flight f " +
//            "WHERE f.departureAirport.city = :departureCity " +
//            "AND f.arrivalAirport.city = :arrivalCity " +
//            "AND f.departureDate = :departureDate " +
//            "AND :returnDate IS NULL OR f.returnDate = :returnDate")
//    List<Flight> findFlightsByCitiesAndDates(
//            String departureCity, String arrivalCity,
//            LocalDate departureDate, LocalDate returnDate);

    List<Flight> findFlightsByDepartureAirport_CityAndArrivalAirport_CityAndDepartureDateAndReturnDate(
            String departureCity, String arrivalCity, LocalDate departureDate, LocalDate returnDate);
}
