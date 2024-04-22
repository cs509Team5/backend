package dto;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewRequestTest {

  @Test
  public void testViewRequestConstructor() {
    String departureAirport = "AirportA";
    String flightNumber = "FL123";
    Date departureDate = Date.valueOf("2021-12-28");

    ViewRequest viewRequest = new ViewRequest(departureAirport, flightNumber, departureDate);

    assertEquals(departureAirport, viewRequest.getDepartureAirport());
    assertEquals(flightNumber, viewRequest.getFlightNumber());
    assertEquals(departureDate, viewRequest.getDepartureDate());
  }
}
