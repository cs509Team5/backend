package com.example.arst5backend.service.reserve;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.service.reserve.IReserveDetail;
import com.example.arst5backend.service.reserve.ReserveDetail;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.service.reserve.IReserveDetail;
import com.example.arst5backend.service.reserve.ReserveDetail;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;

public class ReserveDetailTest {

  @Test
  public void testDetail() {

    FlightCapacity flightCapacity = Mockito.mock(FlightCapacity.class);
    Mockito.when(flightCapacity.getDepartairport()).thenReturn("JFK");
    Mockito.when(flightCapacity.getArriveairport()).thenReturn("LAX");
    Mockito.when(flightCapacity.getDepartdatetime()).thenReturn(Timestamp.valueOf("2022-01-25 08:00:00"));
    Mockito.when(flightCapacity.getArrivedatetime()).thenReturn(Timestamp.valueOf("2022-01-25 10:00:00"));
    Mockito.when(flightCapacity.getFlightnumber()).thenReturn("DL1234");

    IReserveDetail reserveDetail = new ReserveDetail();

    String result = reserveDetail.detail(flightCapacity);

    String expectedResult = "You have successfully reserved a flight ticket." +
      "\nYour flight ticket information is as follows: " +
      "\nDeparture Airport: JFK" +
      "\nArrival Airport: LAX" +
      "\nDeparture Time: 2022-01-25 08:00:00.0" +
      "\nArrival Time: 2022-01-25 10:00:00.0" +
      "\nFlight Number: DL1234";
    assertEquals(expectedResult, result);
  }
}
