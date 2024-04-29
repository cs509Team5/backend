package com.example.arst5backend.service.reserve;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.FlightsReserveRepository;
import com.example.arst5backend.service.airlines.FlightReserveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReserveServiceTest {
  @Mock
  private FlightReserveService flightReserveService;

  @Mock
  private FlightsReserveRepository flightsReserveRepository;

  @Mock
  private IReserveDetail reserveDetail;

  private ReserveService reserveService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    reserveService = new ReserveService(flightReserveService, flightsReserveRepository, reserveDetail);
  }

  @Test
  public void testReserveFlights_FirstClassAvailable() {
    // Mock data
    String DepartAirport = "JFK";
    String ArriveAirport = "LAX";
    String FlightNumber = "DL1234";
    String SeatType = "FirstClass";
    Timestamp DepartDate = new Timestamp(System.currentTimeMillis());
    Timestamp departure_min_timestamp = new Timestamp(DepartDate.getTime());
    Timestamp departure_max_timestamp = new Timestamp(DepartDate.getTime() + (24 * 60 * 60 * 1000));
    java.sql.Date departDate = new java.sql.Date(DepartDate.getTime());

    FlightCapacity flightCapacity = new FlightCapacity();
    flightCapacity.setFirstclassnum(1);
    List<FlightCapacity> flightCapacityList = new ArrayList<>();
    flightCapacityList.add(flightCapacity);

    when(flightReserveService.searchFlights(DepartAirport, ArriveAirport, FlightNumber, departure_min_timestamp, departure_max_timestamp)).thenReturn(flightCapacityList);
    when(flightsReserveRepository.saveAndFlush(any(FlightCapacity.class))).thenReturn(null);
    when(reserveDetail.detail(any(FlightCapacity.class))).thenReturn("Reservation details");

    String result = reserveService.reserveFlights(DepartAirport, ArriveAirport, FlightNumber, SeatType, departDate);

    assertEquals("Reservation details\nSeat Type: FirstClass", result);
  }

  @Test
  public void testReserveFlights_FirstClassNotAvailable() {
    // Mock data
    String DepartAirport = "JFK";
    String ArriveAirport = "LAX";
    String FlightNumber = "DL1234";
    String SeatType = "FirstClass";
    Timestamp DepartDate = new Timestamp(System.currentTimeMillis());
    Timestamp departure_min_timestamp = new Timestamp(DepartDate.getTime());
    Timestamp departure_max_timestamp = new Timestamp(DepartDate.getTime() + (24 * 60 * 60 * 1000));
    java.sql.Date departDate = new java.sql.Date(DepartDate.getTime());

    FlightCapacity flightCapacity = new FlightCapacity();
    flightCapacity.setFirstclassnum(0);
    List<FlightCapacity> flightCapacityList = new ArrayList<>();
    flightCapacityList.add(flightCapacity);

    when(flightReserveService.searchFlights(DepartAirport, ArriveAirport, FlightNumber, departure_min_timestamp, departure_max_timestamp)).thenReturn(flightCapacityList);

    String result = reserveService.reserveFlights(DepartAirport, ArriveAirport, FlightNumber, SeatType, departDate);

    assertEquals("This ticket has been sold out!", result);
  }
}
