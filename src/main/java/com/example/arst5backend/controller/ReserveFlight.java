package com.example.arst5backend.controller;

import com.example.arst5backend.service.ReserveService;
import com.example.arst5backend.service.SearchService;
import com.fasterxml.jackson.annotation.JsonFormat;
import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/reserve")
public class ReserveFlight {
    private final ReserveService reserveService;

    @Autowired
    public ReserveFlight(ReserveService reserveService){
        this.reserveService = reserveService;
    }

    @GetMapping
    public ReserveResponse reserve(@RequestBody ReserveRequest reserveRequest) {
        System.out.println(reserveRequest);
        ReserveResponse reserveResponse = new ReserveResponse();
        String departureStatus = reserveService.reserveFlights(
                reserveRequest.getDepartureAirport(),
                reserveRequest.getArrivalAirport(),
                reserveRequest.getFlightNumber(),
                reserveRequest.getSeatType(),
                reserveRequest.getDepartureDate()
        );
        reserveResponse.setBookingDetails(departureStatus);
        return reserveResponse;
    }
}
