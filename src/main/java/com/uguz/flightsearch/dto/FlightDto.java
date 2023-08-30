package com.uguz.flightsearch.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FlightDto {
    private long departureAirportId;
    private long arrivalAirportId;
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private BigDecimal price;

}
