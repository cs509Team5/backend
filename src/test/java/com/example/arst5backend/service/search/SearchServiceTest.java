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
  private SearchStopoverService searchStopoverService;

  @Mock
  private SearchNonStopoverService searchNonStopoverService;

  @InjectMocks
  private SearchService searchService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testSearchFlights_1Stopover() {
    // Setup
    String departAirport = "JFK";
    String arriveAirport = "LAX";
    int numberOfStopover = 1;
    Boolean acceptEconomy = true;
    Boolean acceptFirstClass = false;
    Date departDate = new Date(System.currentTimeMillis());
    // Expected results
    List<FlightInfo> nonStopoverFlights = new ArrayList<>();

    List<FlightInfo> stopoverFlights = new ArrayList<>();

    // Stubbing
    when(searchNonStopoverService.searchFlights(departAirport, arriveAirport, 0, acceptEconomy, acceptFirstClass, departDate))
      .thenReturn(nonStopoverFlights);
    when(searchStopoverService.searchFlights(departAirport, arriveAirport, numberOfStopover, acceptEconomy, acceptFirstClass, departDate))
      .thenReturn(stopoverFlights);
    // Execution
    List<FlightInfo> result = searchService.searchFlights(departAirport, arriveAirport, numberOfStopover, acceptEconomy, acceptFirstClass, departDate);
    // Verification
    List<FlightInfo> expectedResults = new ArrayList<>();
    for (FlightInfo item: nonStopoverFlights){
      expectedResults.add(item);
    }
    for (FlightInfo item: stopoverFlights) {
      expectedResults.add(item);
    }
    assertEquals(expectedResults, result); // 1 from each service
  }
  @Test
  public void testSearchFlights_NonStopover() {

    String departAirport = "JFK";
    String arriveAirport = "LAX";
    int numberOfStopover = 0;
    Boolean acceptEconomy = true;
    Boolean acceptFirstClass = false;
    Date departDate = new Date(System.currentTimeMillis());

    List<FlightInfo> nonStopoverFlights = new ArrayList<>();

    when(searchNonStopoverService.searchFlights(departAirport, arriveAirport, numberOfStopover, acceptEconomy, acceptFirstClass, departDate))
      .thenReturn(nonStopoverFlights);

    List<FlightInfo> result = searchService.searchFlights(departAirport, arriveAirport, numberOfStopover, acceptEconomy, acceptFirstClass, departDate);
    assertEquals(nonStopoverFlights, result);
  }
}
