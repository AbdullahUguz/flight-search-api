package com.uguz.flightsearch.controllers;

import com.uguz.flightsearch.business.service.AirportService;
import com.uguz.flightsearch.config.swagger.AuthorizationInfo;
import com.uguz.flightsearch.dto.AirportDto;
import com.uguz.flightsearch.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/airports")
public class AirportController {
    private AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService){
        this.airportService=airportService;
    }

    @AuthorizationInfo
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AirportDto airportDto){
        try {
            Airport airport = this.airportService.create(airportDto);
            if(airport == null){
                return new ResponseEntity<>("City Not Found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(airport, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Exception : "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @AuthorizationInfo
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try {
            return new ResponseEntity<>(this.airportService.getAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Exception : "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @AuthorizationInfo
    @PutMapping("/update/{airportId}")
    public ResponseEntity<?> update(@PathVariable int airportId,@RequestBody AirportDto airportDto){
        try {
            Airport airport = this.airportService.update(Long.valueOf(airportId),airportDto);
            if(airport == null){
                return new ResponseEntity<>("City Not Found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(airport, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Exception : "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @AuthorizationInfo
    @DeleteMapping("/delete/{airportId}")
    public ResponseEntity<String> delete(@PathVariable int airportId){
        try {
            this.airportService.delete(Long.valueOf(airportId));
            return new ResponseEntity<>("Airport deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
