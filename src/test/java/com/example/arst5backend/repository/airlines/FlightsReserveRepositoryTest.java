package com.example.arst5backend.repository.airlines;

import com.example.arst5backend.service.airlines.FlightReserveService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Timestamp;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class FlightsReserveRepositoryTest {

  @InjectMocks
  private FlightReserveService flightReserveService;

  @Mock
  private FlightsReserveRepository flightsReserveRepository;
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSomeMethod() {
    when(flightsReserveRepository.findByDepartairportAndArriveairportAndFlightnumberAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
      anyString(), anyString(), anyString(), any(Timestamp.class), any(Timestamp.class)))
      .thenReturn(new ArrayList<>());
  }

}
