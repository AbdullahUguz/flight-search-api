package com.uguz.flightsearch.business.impl;

import com.uguz.flightsearch.business.service.FlightService;
import com.uguz.flightsearch.dto.FlightDto;
import com.uguz.flightsearch.entity.Airport;
import com.uguz.flightsearch.entity.Flight;
import com.uguz.flightsearch.repository.AirportRepository;
import com.uguz.flightsearch.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightImpl implements FlightService {

    private FlightRepository flightRepository;
    private AirportRepository airportRepository;


    @Autowired
    public FlightImpl(FlightRepository flightRepository,AirportRepository airportRepository){
        this.flightRepository=flightRepository;
        this.airportRepository=airportRepository;
    }

    @Override
    public Flight create(FlightDto flightDto) {
        Airport departureAirport = this.airportRepository.findById(flightDto.getDepartureAirportId()).get();
        Airport arrivalAirport = this.airportRepository.findById(flightDto.getArrivalAirportId()).get();

        if(departureAirport == null || arrivalAirport ==null){
            return null;
        }

        Flight flight = new Flight();
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setReturnTime(flightDto.getReturnTime());
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

        if(departureAirport == null || arrivalAirport ==null || flight ==null){
            return null;
        }

        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setReturnTime(flightDto.getReturnTime());
        flight.setPrice(flightDto.getPrice());

        return this.flightRepository.save(flight);
    }

    @Override
    public void delete(long flightId) {
        this.flightRepository.deleteById(flightId);
    }
}
