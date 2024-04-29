package com.example.arst5backend.service.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.arst5backend.model.airlines.FlightCapacity;
import dto.FlightInfo;
import com.example.arst5backend.service.airlines.ISearchTickets;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SearchNonStopoverServiceTest {

  @Mock private ISearchTickets searchTickets;

  @InjectMocks private SearchNonStopoverService searchNonStopoverService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testSearchDetail() {

    String DepartAirport = "JFK";
    String ArriveAirport = "LAX";
    int numberOfStopover = 1;
    Boolean AcceptEconomy = true;
    Boolean AcceptFirstClass = true;
    Date DepartDate = new Date(System.currentTimeMillis());

    // Get a Calendar instance and set the time to DepartDate
    Calendar departCalendarMin = Calendar.getInstance();
    departCalendarMin.setTime(DepartDate);

    // Reset hour, minute, second, and millisecond
    departCalendarMin.set(Calendar.HOUR_OF_DAY, 0);
    departCalendarMin.set(Calendar.MINUTE, 0);
    departCalendarMin.set(Calendar.SECOND, 0);
    departCalendarMin.set(Calendar.MILLISECOND, 0);

    Timestamp departure_min_timestamp = new Timestamp(departCalendarMin.getTimeInMillis());
    // Construct the max time stamp.
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(DepartDate);
    calendar.add(Calendar.DAY_OF_MONTH, 1);
    Timestamp departure_max_timestamp = new Timestamp(calendar.getTimeInMillis());

    List<FlightCapacity> flightCapacityList = new ArrayList<>();
    FlightCapacity flightCapacity = new FlightCapacity();
    flightCapacity.setEconomyclassnum(10);
    flightCapacity.setFirstclassnum(5);
    flightCapacity.setDepartairport(DepartAirport);
    flightCapacity.setArriveairport(ArriveAirport);
    flightCapacity.setDepartdatetime(departure_min_timestamp);
    flightCapacity.setArrivedatetime(departure_max_timestamp);
    flightCapacity.setFlightnumber("DL1234");
    flightCapacityList.add(flightCapacity);


    when(
      searchTickets.searchTickets(
        DepartAirport, ArriveAirport, departure_min_timestamp, departure_max_timestamp))
      .thenReturn(flightCapacityList);

    List<FlightInfo> result =
      searchNonStopoverService.searchFlights(
        DepartAirport, ArriveAirport, numberOfStopover, AcceptEconomy, AcceptFirstClass, DepartDate);

    assertEquals(1, result.size());
    FlightInfo flightInfo = result.get(0);
    assertEquals("Economy and first class options available", flightInfo.getSeatClass());
    assertEquals(DepartAirport, flightInfo.getDepartAirport());
    assertEquals(ArriveAirport, flightInfo.getArriveAirport());
    assertEquals(departure_min_timestamp, flightInfo.getDepartDatetime());
    assertEquals(departure_max_timestamp, flightInfo.getArriveDatetime());
    assertEquals("DL1234", flightInfo.getFlightNumber());
    assertEquals("Direct Flight", flightInfo.getIsLayover());
  }
}

