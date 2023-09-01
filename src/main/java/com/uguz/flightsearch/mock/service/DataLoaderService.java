package com.uguz.flightsearch.mock.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface DataLoaderService {
    void loadFlightData() throws JsonProcessingException;
}
