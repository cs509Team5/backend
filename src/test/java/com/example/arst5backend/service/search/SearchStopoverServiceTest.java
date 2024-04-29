package com.example.arst5backend.service.search;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.service.airlines.SearchTicketsStopoverArrive;
import com.example.arst5backend.service.airlines.SearchTicketsStopoverDepart;
import dto.FlightInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchStopoverServiceTest {

  @Mock
  private SearchTicketsStopoverDepart searchTicketsStopoverDepart;
  @Mock
  private SearchTicketsStopoverArrive searchTicketsStopoverArrive;
  @Mock
  private SetFlight setFlight;

  @InjectMocks
  private SearchStopoverService searchStopoverService;
  private Date departDate;

  @BeforeEach
  void setUp() {
    List<FlightInfo> flights = new ArrayList<>();
    MockitoAnnotations.openMocks(this);
    Calendar cal = Calendar.getInstance();
    cal.set(2022, Calendar.APRIL, 20);
    departDate = new Date(cal.getTimeInMillis());
    FlightCapacity sampleFlight1, sampleFlight2;
    sampleFlight1 = new FlightCapacity();
    sampleFlight1.setDepartairport("JFK");
    sampleFlight1.setArriveairport("LAX");
    sampleFlight1.setDepartdatetime(new Timestamp(cal.getTimeInMillis()));
    sampleFlight1.setArrivedatetime(new Timestamp(cal.getTimeInMillis() + 8 * 3600 * 1000)); // 8 hours later
    sampleFlight1.setFlightnumber("DL1234");
    sampleFlight1.setFlighttype("Non-stop");
    sampleFlight1.setEconomyclassnum(10);
    sampleFlight1.setFirstclassnum(5);
    when(searchTicketsStopoverDepart.searchTickets(any(), any(), any(), any())).thenReturn(List.of(sampleFlight1));

    sampleFlight2 = new FlightCapacity();
    sampleFlight2.setDepartairport("LAX");
    sampleFlight2.setArriveairport("BOS");
    cal.add(Calendar.HOUR, 9);
    sampleFlight2.setDepartdatetime(new Timestamp(cal.getTimeInMillis()));
    sampleFlight2.setArrivedatetime(new Timestamp(cal.getTimeInMillis() + 9 * 3600 * 1000));
    sampleFlight2.setFlightnumber("DL1233");
    sampleFlight2.setFlighttype("Non-stop");
    sampleFlight2.setEconomyclassnum(15);
    sampleFlight2.setFirstclassnum(10);

    when(searchTicketsStopoverArrive.searchTickets(any(), any(), any(), any())).thenReturn(List.of(sampleFlight2));
  }

  @Test
  public void testSearchDetail_AllClassesAccepted_NonNullDepartTimes() {
    when(setFlight.set(any(FlightCapacity.class), any(FlightInfo.class))).thenAnswer(invocation -> {
      FlightCapacity cap = invocation.getArgument(0);
      FlightInfo info = invocation.getArgument(1);
      info.setDepartAirport(cap.getDepartairport());
      info.setArriveAirport(cap.getArriveairport());
      info.setDepartDatetime(cap.getDepartdatetime());
      info.setArriveDatetime(cap.getArrivedatetime());
      info.setFlightNumber("TestFlight123");
      info.setFlightType("Direct");
      return info;
    });

    List<FlightInfo> results = searchStopoverService.searchFlights("JFK", "LAX", 1,true, true, departDate);

    assertFalse(results.isEmpty());
    assertEquals(2, results.size());
    assertNotNull(results.get(0).getDepartDatetime());
    assertNotNull(results.get(1).getDepartDatetime());
    assertTrue(results.get(1).getDepartDatetime().getTime() - results.get(0).getArriveDatetime().getTime() >= 2700 * 1000, "Time between flights is less than 45 minutes");

    assertEquals("Direct", results.get(0).getFlightType());
    assertEquals("Direct", results.get(1).getFlightType());
    assertEquals("First Leg of a Connecting Flight", results.get(0).getIsLayover());
    assertEquals("Second Leg of a Connecting Flight", results.get(1).getIsLayover());
  }
}
