package com.example.arst5backend.service.search;

import com.example.arst5backend.model.airlines.FlightCapacity;
import dto.FlightInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SetFlightTest {

  @Mock
  private FlightCapacity flightResult;

  @InjectMocks
  private SetFlight setFlight;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSet() {

    FlightInfo flight = new FlightInfo();

    when(flightResult.getArriveairport()).thenReturn("JFK");
    when(flightResult.getDepartairport()).thenReturn("LAX");

    FlightInfo result = setFlight.set(flightResult, flight);

    assertEquals("JFK", result.getArriveAirport());
    assertEquals("LAX", result.getDepartAirport());
  }
}
