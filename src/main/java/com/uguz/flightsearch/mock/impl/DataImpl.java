package com.uguz.flightsearch.mock.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uguz.flightsearch.mock.service.DataService;
import com.uguz.flightsearch.entity.Flight;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;

@Service
public class DataImpl implements DataService {

    private final  WebClient webClient = WebClient.create("https://8a0ed25d-d34e-48c7-bec7-d0c7e9ec2f08.mock.pstmn.io");
    @Override
    public List<Flight> fetchFlightData() throws JsonProcessingException {
        String responseBody = webClient.get()
                .uri("/getFlights")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Flight> flights = objectMapper.readValue(responseBody, new TypeReference<List<Flight>>() {});

        System.out.println(responseBody);

        return flights;
    }
}
