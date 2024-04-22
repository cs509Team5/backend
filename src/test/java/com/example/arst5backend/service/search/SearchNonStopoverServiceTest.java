package com.example.arst5backend.service.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.arst5backend.model.airlines.FlightCapacity;
import dto.FlightInfo;
import com.example.arst5backend.repository.airlines.DeltasReserveRepository;
import com.example.arst5backend.service.airlines.ISearchTickets;
import com.example.arst5backend.service.search.ISearchNonStopoverService;
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
    Boolean AcceptEconomy = true;
    Boolean AcceptFirstClass = true;
    Date DepartDate = new Date(System.currentTimeMillis());

    Timestamp departureMinTimestamp = new Timestamp(DepartDate.getTime());
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(DepartDate);
    calendar.add(Calendar.DAY_OF_MONTH, 1);
    Timestamp departureMaxTimestamp = new Timestamp(calendar.getTimeInMillis());

    List<FlightCapacity> flightCapacityList = new ArrayList<>();
    FlightCapacity flightCapacity = new FlightCapacity();
    flightCapacity.setEconomyclassnum(10);
    flightCapacity.setFirstclassnum(5);
    flightCapacity.setDepartairport(DepartAirport);
    flightCapacity.setArriveairport(ArriveAirport);
    flightCapacity.setDepartdatetime(departureMinTimestamp);
    flightCapacity.setArrivedatetime(departureMaxTimestamp);
    flightCapacity.setFlightnumber("DL1234");
    flightCapacityList.add(flightCapacity);


    when(
      searchTickets.searchTickets(
        DepartAirport, ArriveAirport, departureMinTimestamp, departureMaxTimestamp))
      .thenReturn(flightCapacityList);

    List<FlightInfo> result =
      searchNonStopoverService.searchDetail(
        DepartAirport, ArriveAirport, AcceptEconomy, AcceptFirstClass, DepartDate, new ArrayList<>());

    assertEquals(1, result.size());
    FlightInfo flightInfo = result.get(0);
    assertEquals("Economy and first class options available", flightInfo.getSeatClass());
    assertEquals(DepartAirport, flightInfo.getDepartAirport());
    assertEquals(ArriveAirport, flightInfo.getArriveAirport());
    assertEquals(departureMinTimestamp, flightInfo.getDepartDatetime());
    assertEquals(departureMaxTimestamp, flightInfo.getArriveDatetime());
    assertEquals("DL1234", flightInfo.getFlightNumber());
    assertEquals("Direct Flight", flightInfo.getIsLayover());
  }
}

