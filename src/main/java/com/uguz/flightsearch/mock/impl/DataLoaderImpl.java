package com.uguz.flightsearch.mock.impl;

import com.uguz.flightsearch.mock.service.DataLoaderService;
import com.uguz.flightsearch.mock.service.DataService;
import com.uguz.flightsearch.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataLoaderImpl implements DataLoaderService {

    private final DataService flightDataService;
    private final FlightRepository flightRepository;

    @Autowired
    public DataLoaderImpl(DataService flightDataService, FlightRepository flightRepository) {
        this.flightDataService = flightDataService;
        this.flightRepository = flightRepository;
    }

    @Override
//    @Scheduled(cron = "0 0 0 * * ?") // her 00:00 da
//    @Scheduled(cron = "0 * * * * ?")  // her dakika
    public void loadFlightData() {
        try{
            this.flightRepository.saveAll(this.flightDataService.fetchFlightData());
        }catch (Exception e){
            System.out.println("Exception load data : "+e);
        }
    }
}
