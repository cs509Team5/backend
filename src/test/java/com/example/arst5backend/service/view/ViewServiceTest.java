package com.example.arst5backend.service.view;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.FlightsReserveRepository;
import dto.FlightInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ViewServiceTest {

  @Mock
  private FlightsReserveRepository flightsReserveRepository;

  @InjectMocks
  private ViewService viewService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testViewDetail() {

    String departAirport = "JFK";
    Date departDate = new Date();
    String flightNumber = "DL1234";

    FlightCapacity flightCapacity = new FlightCapacity();
    flightCapacity.setArriveairport("JFK");
    flightCapacity.setDepartairport("LAX");
    flightCapacity.setDepartdatetime(new Timestamp(departDate.getTime()));
    flightCapacity.setArrivedatetime(new Timestamp(departDate.getTime() + 3600 * 1000)); // Adding 1 hour
    flightCapacity.setFlightnumber("DL1234");
    flightCapacity.setFlighttype("Domestic");

    List<FlightCapacity> flightCapacityList = new ArrayList<>();
    flightCapacityList.add(flightCapacity);

    when(flightsReserveRepository.findByDepartairportAndFlightnumberAndDepartdatetimeGreaterThanEqual(
      any(String.class), any(String.class), any(Timestamp.class)))
      .thenReturn(flightCapacityList);

    FlightInfo result = viewService.viewDetail(departAirport, departDate, flightNumber);

    assertEquals("JFK", result.getArriveAirport());
    assertEquals("LAX", result.getDepartAirport());
    assertEquals(new Timestamp(departDate.getTime()), result.getDepartDatetime());
    assertEquals(new Timestamp(departDate.getTime() + 3600 * 1000), result.getArriveDatetime());
    assertEquals("DL1234", result.getFlightNumber());
    assertEquals("Domestic", result.getFlightType());
  }
}
