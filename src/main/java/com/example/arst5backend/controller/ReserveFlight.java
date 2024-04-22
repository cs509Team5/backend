package com.example.arst5backend.controller;

import com.example.arst5backend.service.reserve.IReserveService;
import com.example.arst5backend.service.reserve.ReserveService;
import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
public class ReserveFlight {
    private final IReserveService reserveService;

    @Autowired
    public ReserveFlight(IReserveService reserveService){
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
