package com.example.arst5backend.model.airlines;

import org.junit.Test;
import java.sql.Timestamp;
import static org.junit.Assert.assertEquals;

public class FlightCapacityTest {

  @Test
  public void testGetterAndSetter() {
    FlightCapacity flightCapacity = new FlightCapacity();

    Long id = 1L;
    Timestamp departDateTime = new Timestamp(System.currentTimeMillis());
    Timestamp arriveDateTime = new Timestamp(System.currentTimeMillis() + 5000);
    String departAirport = "JFK";
    String arriveAirport = "LAX";
    String flightNumber = "DL1234";
    String flightType = "TypeA";
    int firstClassNum = 10;
    int economyClassNum = 20;

    flightCapacity.setId(id);
    flightCapacity.setDepartdatetime(departDateTime);
    flightCapacity.setArrivedatetime(arriveDateTime);
    flightCapacity.setDepartairport(departAirport);
    flightCapacity.setArriveairport(arriveAirport);
    flightCapacity.setFlightnumber(flightNumber);
    flightCapacity.setFlighttype(flightType);
    flightCapacity.setFirstclassnum(firstClassNum);
    flightCapacity.setEconomyclassnum(economyClassNum);

    assertEquals(id, flightCapacity.getId());
    assertEquals(departDateTime, flightCapacity.getDepartdatetime());
    assertEquals(arriveDateTime, flightCapacity.getArrivedatetime());
    assertEquals(departAirport, flightCapacity.getDepartairport());
    assertEquals(arriveAirport, flightCapacity.getArriveairport());
    assertEquals(flightNumber, flightCapacity.getFlightnumber());
    assertEquals(flightType, flightCapacity.getFlighttype());
    assertEquals(firstClassNum, flightCapacity.getFirstclassnum());
    assertEquals(economyClassNum, flightCapacity.getEconomyclassnum());
  }
}
