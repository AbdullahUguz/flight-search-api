package com.uguz.flightsearch.business.concretes;

import com.uguz.flightsearch.business.abstracts.AirportService;
import com.uguz.flightsearch.dto.AirportDto;
import com.uguz.flightsearch.entities.Airport;
import com.uguz.flightsearch.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportManager implements AirportService {
    private AirportRepository airportRepository;

    @Autowired
    public AirportManager(AirportRepository airportRepository){
        this.airportRepository=airportRepository;
    }

    @Override
    public Airport create(Airport airport) {
        return this.airportRepository.save(airport);
    }

    @Override
    public List<Airport> getAll() {
        return this.airportRepository.findAll();
    }

    @Override
    public Airport update(long airportId, AirportDto airportDto) {
        Airport airport = this.airportRepository.findById(airportId).get();
        if(airport == null){
            return null;
        }
        airport.setCity(airportDto.getCity());
        return this.airportRepository.save(airport);
    }

    @Override
    public void delete(long airportId) {
        this.airportRepository.deleteById(airportId);
    }


}
