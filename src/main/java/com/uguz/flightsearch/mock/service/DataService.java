package com.uguz.flightsearch.mock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uguz.flightsearch.entity.Flight;

import java.util.List;

public interface DataService {
    List<Flight> fetchFlightData() throws JsonProcessingException;
}
