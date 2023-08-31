package com.uguz.flightsearch.controllers;

import com.uguz.flightsearch.business.service.FlightService;
import com.uguz.flightsearch.config.swagger.AuthorizationInfo;
import com.uguz.flightsearch.dto.FlightDto;
import com.uguz.flightsearch.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/flights")
public class FlightController {
    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @AuthorizationInfo
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody FlightDto flightDto) {
        try {
            return new ResponseEntity<>(this.flightService.create(flightDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception : "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @AuthorizationInfo
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(this.flightService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception : "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @AuthorizationInfo
    @PutMapping("/update/{flightId}")
    public ResponseEntity<?> update(@PathVariable int flightId, @RequestBody FlightDto flightDto) {
        try {
            return new ResponseEntity<>(this.flightService.update(Long.valueOf(flightId), flightDto), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Exception : "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @AuthorizationInfo
    @DeleteMapping("/delete/{flightId}")
    public ResponseEntity<?> delete(@PathVariable int flightId) {
        try {
            this.flightService.delete(Long.valueOf(flightId));
            return new ResponseEntity<>("Flight deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception : "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @AuthorizationInfo
    @PostMapping("/findFlight")
    public ResponseEntity<?>
    findFlightByFilteringConditions(
            @RequestParam String departureAirport
            , @RequestParam String arrivalAirport
             ,@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate departureDate
            , @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate returnDate
    ) {

        try {
            List<Flight> flights = this.flightService.findFlight(departureAirport,arrivalAirport,departureDate,returnDate);
            if(flights.isEmpty()){
                return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception : "+e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

}
