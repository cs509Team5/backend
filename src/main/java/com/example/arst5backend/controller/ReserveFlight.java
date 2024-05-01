package com.example.arst5backend.controller;

import com.example.arst5backend.service.reserve.IReserveService;
import dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserve")
@CrossOrigin(origins = "*")
public class ReserveFlight {
  private final IReserveService reserveService;

  @Autowired
  public ReserveFlight(IReserveService reserveService) {
    this.reserveService = reserveService;
  }

  @PostMapping
  public ReserveResponse reserve(@RequestBody ReserveRequest reserveRequest) {
    System.out.println(reserveRequest);
    ReserveResponse reserveResponse = new ReserveResponse();
    String departureStatus = reserveService.reserveFlights(
        reserveRequest.getDepartureAirport(),
        reserveRequest.getArrivalAirport(),
        reserveRequest.getFlightNumber(),
        reserveRequest.getSeatType(),
        reserveRequest.getDepartureDate());
    reserveResponse.setBookingDetails(departureStatus);
    return reserveResponse;
  }
}
