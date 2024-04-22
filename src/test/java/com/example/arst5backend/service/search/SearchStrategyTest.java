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
import static org.mockito.Mockito.when;

public class SearchStrategyTest {

  @Mock
  private ISearchService searchService;

  @InjectMocks
  private SearchStrategy searchStrategy;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }
  @Test
  public void testEvaluate_NonStopover() {

    String departAirport = "JFK";
    String arriveAirport = "LAX";
    int numberOfStopover = 0;
    boolean acceptEconomy = true;
    boolean acceptFirstClass = true;
    Date departDate = new Date(System.currentTimeMillis());
    List<FlightInfo> expectedFlights = new ArrayList<>();

    when(searchService.searchFlights(departAirport, arriveAirport, new ArrayList<>(List.of(0, 0)), acceptEconomy, acceptFirstClass, departDate))
      .thenReturn(expectedFlights);

    List<FlightInfo> result = searchStrategy.evaluate(departAirport, arriveAirport, numberOfStopover, acceptEconomy, acceptFirstClass, departDate);

    assertEquals(expectedFlights, result);
  }

  @Test
  public void testEvaluate_1Stopover() {
    // Mock data
    String departAirport = "JFK";
    String arriveAirport = "LAX";
    int numberOfStopover = 1;
    boolean acceptEconomy = true;
    boolean acceptFirstClass = true;
    Date departDate = new Date(System.currentTimeMillis());
    List<FlightInfo> expectedFlights = new ArrayList<>();
    when(searchService.searchFlights(departAirport, arriveAirport, new ArrayList<>(List.of(0, 1)), acceptEconomy, acceptFirstClass, departDate))
      .thenReturn(expectedFlights);

    List<FlightInfo> result = searchStrategy.evaluate(departAirport, arriveAirport, numberOfStopover, acceptEconomy, acceptFirstClass, departDate);

    assertEquals(expectedFlights, result);
  }
}
