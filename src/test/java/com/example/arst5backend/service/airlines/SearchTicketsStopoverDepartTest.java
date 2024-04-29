package com.example.arst5backend.service.airlines;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.FlightsReserveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class SearchTicketsStopoverDepartTest {

  @Mock
  private FlightsReserveRepository flightsReserveRepositoryMock;

  @InjectMocks
  private SearchTicketsStopoverDepart searchTicketsStopoverDepart;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testSearchTicketsDepart() {

    List<FlightCapacity> expectedFlights = Arrays.asList(
      new FlightCapacity(),
      new FlightCapacity()
    );
    when(flightsReserveRepositoryMock.findByDepartairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
      anyString(), any(Timestamp.class), any(Timestamp.class)))
      .thenReturn(expectedFlights);

    List<FlightCapacity> actualFlights = searchTicketsStopoverDepart.searchTickets(
      "LAX",
      "BOS",
      Timestamp.valueOf("2022-01-20 00:00:00"),
      Timestamp.valueOf("2022-01-30 00:00:00")
    );

    assertEquals(expectedFlights.size(), actualFlights.size());
    verify(flightsReserveRepositoryMock, times(1)).findByDepartairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
      anyString(), any(Timestamp.class), any(Timestamp.class));
  }
}
