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

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Autowired
    public FlightImpl(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public Flight create(FlightDto flightDto) {
        Optional<Airport> departureAirport = this.airportRepository.findById(flightDto.getDepartureAirportId());
        Optional<Airport> arrivalAirport = this.airportRepository.findById(flightDto.getArrivalAirportId());

        if (departureAirport.isEmpty() || arrivalAirport.isEmpty()) {
            return null;
        }

        Flight flight = new Flight();
        flight.setArrivalAirport(arrivalAirport.get());
        flight.setDepartureAirport(departureAirport.get());
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
        Optional<Flight> flight = this.flightRepository.findById(flightId);

        Optional<Airport> departureAirport = this.airportRepository.findById(flightDto.getDepartureAirportId());
        Optional<Airport> arrivalAirport = this.airportRepository.findById(flightDto.getArrivalAirportId());

        if (departureAirport.isEmpty() || arrivalAirport.isEmpty() || flight.isEmpty()) {
            return null;
        }

        flight.get().setArrivalAirport(arrivalAirport.get());
        flight.get().setDepartureAirport(departureAirport.get());
        flight.get().setDepartureDate(flightDto.getDepartureDate());
        flight.get().setReturnDate(flightDto.getReturnDate());
        flight.get().setPrice(flightDto.getPrice());

        return this.flightRepository.save(flight.get());
    }

    @Override
    public void delete(long flightId) {
        this.flightRepository.deleteById(flightId);
    }

    @Override
    public List<Map<FlightType,List<Flight>>> findFlight(String departureAirport, String arrivalAirport, LocalDate departureDate, LocalDate returnDate) {

        List<Map<FlightType,List<Flight>>> flightList = new ArrayList<>();

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
