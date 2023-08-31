package com.uguz.flightsearch.business.impl;

import com.uguz.flightsearch.business.service.FlightService;
import com.uguz.flightsearch.constant.FlightType;
import com.uguz.flightsearch.dto.FlightDto;
import com.uguz.flightsearch.entity.Airport;
import com.uguz.flightsearch.entity.Flight;
import com.uguz.flightsearch.repository.AirportRepository;
import com.uguz.flightsearch.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.uguz.flightsearch.constant.FlightType.ONE_WAY;
import static com.uguz.flightsearch.constant.FlightType.ROUND_WAY;

@Service
public class FlightImpl implements FlightService {

    private FlightRepository flightRepository;
    private AirportRepository airportRepository;


    @Autowired
    public FlightImpl(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public Flight create(FlightDto flightDto) {
        Airport departureAirport = this.airportRepository.findById(flightDto.getDepartureAirportId()).get();
        Airport arrivalAirport = this.airportRepository.findById(flightDto.getArrivalAirportId()).get();

        if (departureAirport == null || arrivalAirport == null) {
            return null;
        }

        Flight flight = new Flight();
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);
        flight.setDepartureDate(flightDto.getDepartureDate());
        flight.setReturnDate(flightDto.getReturnDate());
        flight.setPrice(flightDto.getPrice());

        return this.flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAll() {
        return this.flightRepository.findAll();
    }

    @Override
    public Flight update(long flightId, FlightDto flightDto) {
        Flight flight = this.flightRepository.findById(flightId).get();

        Airport departureAirport = this.airportRepository.findById(flightDto.getDepartureAirportId()).get();
        Airport arrivalAirport = this.airportRepository.findById(flightDto.getArrivalAirportId()).get();

        if (departureAirport == null || arrivalAirport == null || flight == null) {
            return null;
        }

        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);
        flight.setDepartureDate(flightDto.getDepartureDate());
        flight.setReturnDate(flightDto.getReturnDate());
        flight.setPrice(flightDto.getPrice());

        return this.flightRepository.save(flight);
    }

    @Override
    public void delete(long flightId) {
        this.flightRepository.deleteById(flightId);
    }

    @Override
    public List<Map> findFlight(String departureAirport, String arrivalAirport, LocalDate departureDate, LocalDate returnDate) {

        List<Map> flightList = new ArrayList<>();

        Map<FlightType,List<Flight>> oneWay =new HashMap<>();

        Map<FlightType,List<Flight>> roundWay =new HashMap<>();

        List<Flight> oneWayFlights = this.flightRepository
                .findFlightsByDepartureAirport_CityAndArrivalAirport_CityAndDepartureDate(
                        departureAirport.toUpperCase(Locale.ROOT)
                        , arrivalAirport.toUpperCase(Locale.ROOT)
                        , departureDate);

        oneWay.put(ONE_WAY,oneWayFlights);
        flightList.add(oneWay);

        if (returnDate != null) {
            List<Flight> returnFlights = this.flightRepository
                    .findFlightsByDepartureAirport_CityAndArrivalAirport_CityAndDepartureDate(
                             arrivalAirport.toUpperCase(Locale.ROOT)
                            , departureAirport.toUpperCase(Locale.ROOT)
                            , returnDate);

            roundWay.put(ROUND_WAY,returnFlights);
            flightList.add(roundWay);

            return flightList;
        }
        return flightList;
    }
}
