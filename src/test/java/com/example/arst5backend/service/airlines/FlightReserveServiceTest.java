package com.example.arst5backend.service.airlines;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.FlightsReserveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Timestamp;
import java.util.List;
import static junit.framework.TestCase.assertNotNull;


public class FlightReserveServiceTest {

  @Mock
  private FlightsReserveRepository flightsReserveRepository;

  @InjectMocks
  private FlightReserveService flightReserveService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSearchFlights() {
    List<FlightCapacity> result = flightReserveService.searchFlights(
      "JFK", "LAX", "FlightNumber",
      Timestamp.valueOf("2022-01-01 00:00:00"), Timestamp.valueOf("2022-01-02 00:00:00"));

    assertNotNull(result);
  }
}
