package com.uguz.flightsearch.business.service;

import com.uguz.flightsearch.dto.AirportDto;
import com.uguz.flightsearch.entity.Airport;

import java.util.List;

public interface AirportService {
    Airport create(AirportDto airportDto);
    List<Airport> getAll();
    Airport update(long airportId, AirportDto airportDto);
    void delete(long airportId);
}
