package com.example.arst5backend.controller;

import com.example.arst5backend.service.reserve.IReserveService;
import dto.ReserveRequest;
import dto.ReserveResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Date;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ReserveFlightTest {

  private ReserveFlight reserveFlight;

  @Mock
  private IReserveService reserveService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    reserveFlight = new ReserveFlight(reserveService);
  }

  @Test
  public void testReserve() {
    // Mocking reserve service behavior
    when(reserveService.reserveFlights(anyString(), anyString(), anyString(), anyString(), any(Date.class)))
      .thenReturn("Booking successful");

    // Creating a sample reserve request
    ReserveRequest reserveRequest = new ReserveRequest();
    reserveRequest.setDepartureAirport("JFK");
    reserveRequest.setArrivalAirport("LAX");
    reserveRequest.setFlightNumber("DL1234");
    reserveRequest.setSeatType("Economy");
    reserveRequest.setDepartureDate(Date.valueOf("2022-01-20"));

    // Calling the reserve method of the controller
    ReserveResponse reserveResponse = reserveFlight.reserve(reserveRequest);

    // Verifying the response
    assertEquals("Booking successful", reserveResponse.getBookingDetails());
  }
}
