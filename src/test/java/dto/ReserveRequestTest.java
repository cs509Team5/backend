package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.util.Calendar;
public class ReserveRequestTest {
  @Test
  public void testReserveRequestConstructorAndGetters() {
    String departureAirport = "JFK";
    String arrivalAirport = "LAX";
    Calendar calendar = Calendar.getInstance();
    calendar.set(2021, Calendar.DECEMBER, 28);
    long timeInMillis = calendar.getTimeInMillis();

    Date departureDate = new Date(timeInMillis);
    String flightNumber = "DL1234";
    String seatType = "Economy";

    ReserveRequest reserveRequest = new ReserveRequest(departureAirport, arrivalAirport, departureDate, flightNumber, seatType);

    assertEquals(departureAirport, reserveRequest.getDepartureAirport());
    assertEquals(arrivalAirport, reserveRequest.getArrivalAirport());
    assertEquals(departureDate, reserveRequest.getDepartureDate());
    assertEquals(flightNumber, reserveRequest.getFlightNumber());
    assertEquals(seatType, reserveRequest.getSeatType());
  }
}
