package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

public class FlightInfoTest {

  @Test
  public void testFlightInfoConstructorAndGetters() {

    Timestamp departDatetime = new Timestamp(System.currentTimeMillis());
    Timestamp arriveDatetime = new Timestamp(System.currentTimeMillis() + 3600 * 1000); // Adding 1 hour
    String departAirport = "JFK";
    String arriveAirport = "LAX";
    String flightNumber = "DL1234";
    String seatClass = "Economy";
    String flightType = "international";
    String isLayover = "Yes";
    Double flightPrice = 90.00;

    FlightInfo flightInfo = new FlightInfo(
        departDatetime, arriveDatetime, departAirport, arriveAirport, flightNumber, seatClass, flightType, isLayover,
        flightPrice);

    assertEquals(departDatetime, flightInfo.getDepartDatetime());
    assertEquals(arriveDatetime, flightInfo.getArriveDatetime());
    assertEquals(departAirport, flightInfo.getDepartAirport());
    assertEquals(arriveAirport, flightInfo.getArriveAirport());
    assertEquals(flightNumber, flightInfo.getFlightNumber());
    assertEquals(seatClass, flightInfo.getSeatClass());
    assertEquals(flightType, flightInfo.getFlightType());
    assertEquals(isLayover, flightInfo.getIsLayover());
  }
}
