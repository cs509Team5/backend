package com.example.arst5backend.service.search;

import dto.FlightInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SearchServiceTest {

  @Mock
  private ISearchStopoverService searchStopoverService;

  @Mock
  private ISearchNonStopoverService searchNonStopoverService;

  @InjectMocks
  private SearchService searchService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testSearchFlights_1Stopover() {
    String departAirport = "JFK";
    String arriveAirport = "LAX";
    List<Integer> myList = new ArrayList<>();
    myList.add(1);
    Boolean acceptEconomy = true;
    Boolean acceptFirstClass = false;
    Date departDate = new Date(System.currentTimeMillis());

    List<FlightInfo> nonStopoverFlights = new ArrayList<>();
    nonStopoverFlights.add(new FlightInfo());

    List<FlightInfo> stopoverFlights = new ArrayList<>();
    stopoverFlights.add(new FlightInfo());

    when(searchNonStopoverService.searchDetail(departAirport, arriveAirport, acceptEconomy, acceptFirstClass, departDate, new ArrayList<>()))
      .thenReturn(nonStopoverFlights);

    when(searchStopoverService.searchDetail(departAirport, arriveAirport, acceptEconomy, acceptFirstClass, departDate, nonStopoverFlights))
      .thenReturn(stopoverFlights);

    List<FlightInfo> result = searchService.searchFlights(departAirport, arriveAirport, myList, acceptEconomy, acceptFirstClass, departDate);

    assertEquals(2, result.size());
    verify(searchNonStopoverService, times(1)).searchDetail(departAirport, arriveAirport, acceptEconomy, acceptFirstClass, departDate, new ArrayList<>());
    verify(searchStopoverService, times(1)).searchDetail(departAirport, arriveAirport, acceptEconomy, acceptFirstClass, departDate, nonStopoverFlights);
  }

  @Test
  public void testSearchFlights_NonStopover() {

    String departAirport = "JFK";
    String arriveAirport = "LAX";
    List<Integer> myList = new ArrayList<>();
    myList.add(0);
    Boolean acceptEconomy = true;
    Boolean acceptFirstClass = false;
    Date departDate = new Date(System.currentTimeMillis());

    List<FlightInfo> nonStopoverFlights = new ArrayList<>();
    nonStopoverFlights.add(new FlightInfo());

    when(searchNonStopoverService.searchDetail(departAirport, arriveAirport, acceptEconomy, acceptFirstClass, departDate, new ArrayList<>()))
      .thenReturn(nonStopoverFlights);

    List<FlightInfo> result = searchService.searchFlights(departAirport, arriveAirport, myList, acceptEconomy, acceptFirstClass, departDate);

    assertEquals(1, result.size());
    verify(searchNonStopoverService, times(1)).searchDetail(departAirport, arriveAirport, acceptEconomy, acceptFirstClass, departDate, new ArrayList<>());
  }
}
