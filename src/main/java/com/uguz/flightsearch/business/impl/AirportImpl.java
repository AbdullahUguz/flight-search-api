package com.uguz.flightsearch.business.impl;

import com.uguz.flightsearch.business.service.AirportService;
import com.uguz.flightsearch.cache.City;
import com.uguz.flightsearch.dto.AirportDto;
import com.uguz.flightsearch.entity.Airport;
import com.uguz.flightsearch.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class AirportImpl implements AirportService {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportImpl(AirportRepository airportRepository){
        this.airportRepository=airportRepository;
    }

    @Override
    public Airport create(AirportDto airportDto) {

        Airport airport = new Airport();
        if(!City.of().isExistCity(airportDto.getCity())){
            return null;
        }
        airport.setCity(airportDto.getCity().toUpperCase(Locale.ROOT));
        return this.airportRepository.save(airport);
    }

    @Override
    public List<Airport> getAll() {
        return this.airportRepository.findAll();
    }

    @Override
    public Airport update(long airportId, AirportDto airportDto) {
        Airport airport = this.airportRepository.findById(airportId).get();

        if(!City.of().isExistCity(airportDto.getCity())){
            return null;
        }
        airport.setCity(airportDto.getCity().toUpperCase(Locale.ROOT));
        return this.airportRepository.save(airport);
    }

    @Override
    public void delete(long airportId) {
        this.airportRepository.deleteById(airportId);
    }

}
