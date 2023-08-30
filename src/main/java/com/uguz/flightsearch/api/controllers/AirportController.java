package com.uguz.flightsearch.api.controllers;

import com.uguz.flightsearch.business.abstracts.AirportService;
import com.uguz.flightsearch.config.swagger.AuthorizationInfo;
import com.uguz.flightsearch.dto.AirportDto;
import com.uguz.flightsearch.entities.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {
    private AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService){
        this.airportService=airportService;
    }

    @AuthorizationInfo
    @PostMapping("/create")
    public ResponseEntity<Airport> create(@RequestBody Airport airport){
        try {
            return new ResponseEntity<>(this.airportService.create(airport), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @AuthorizationInfo
    @GetMapping("/getAll")
    public ResponseEntity<List<Airport>> getAll(){
        try {
            return new ResponseEntity<>(this.airportService.getAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @AuthorizationInfo
    @PutMapping("/update/{airportId}")
    public ResponseEntity<Airport> update(@PathVariable int airportId,@RequestBody AirportDto airportDto){
        try {
            return new ResponseEntity<>(this.airportService.update(Long.valueOf(airportId),airportDto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @AuthorizationInfo
    @DeleteMapping("/delete/{airportId}")
    public ResponseEntity<String> delete(@PathVariable int airportId){
        try {
            this.airportService.delete(airportId);
            return new ResponseEntity<>("Airport deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
