package com.example.arst5backend.controller;

import com.example.arst5backend.service.search.ISearchStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.FlightInfo;
import dto.SearchRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SearchFlightTest {

  private MockMvc mockMvc;

  @Mock
  private ISearchStrategy searchStrategy;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(new SearchFlight(searchStrategy)).build();
  }

  @Test
  public void testSearch() throws Exception {
    List<FlightInfo> departureFlights = Collections.singletonList(new FlightInfo());
    when(searchStrategy.evaluate(anyString(), anyString(), anyInt(), anyBoolean(), anyBoolean(), any()))
      .thenReturn(departureFlights);

    SearchRequest searchRequest = new SearchRequest();
    searchRequest.setDepartureAirport("JFK");
    searchRequest.setArrivalAirport("LAX");
    searchRequest.setNumberOfStopover(0);
    searchRequest.setAcceptEconomy(true);
    searchRequest.setAcceptFirstClass(true);

    mockMvc.perform(post("/search")
        .content(asJsonString(searchRequest))
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  @Test
  public void testSearchWithReturnDate() throws Exception {
    List<FlightInfo> departureFlights = Collections.singletonList(new FlightInfo());
    List<FlightInfo> returnFlights = Collections.singletonList(new FlightInfo());

    when(searchStrategy.evaluate(anyString(), anyString(), anyInt(), anyBoolean(), anyBoolean(), any(Date.class)))
      .thenReturn(departureFlights, returnFlights);

    SearchRequest searchRequest = new SearchRequest();
    searchRequest.setDepartureAirport("JFK");
    searchRequest.setArrivalAirport("LAX");
    searchRequest.setNumberOfStopover(0);
    searchRequest.setAcceptEconomy(true);
    searchRequest.setAcceptFirstClass(false);
    Date departureDate = Date.valueOf("2021-01-22");
    searchRequest.setDepartureDate(departureDate);
    Date returnDate = Date.valueOf("2021-01-25");
    searchRequest.setReturnDate(returnDate);

    mockMvc.perform(post("/search")
        .content(asJsonString(searchRequest))
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());

    verify(searchStrategy, times(2)).evaluate(anyString(), anyString(), anyInt(), anyBoolean(), anyBoolean(), any(Date.class));
  }

  private String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
