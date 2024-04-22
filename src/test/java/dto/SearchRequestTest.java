package dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

public class SearchRequestTest {

  @Test
  public void testGetSetDepartureAirport() {
    SearchRequest request = new SearchRequest();

    request.setDepartureAirport("AirportA");

    assertEquals("AirportA", request.getDepartureAirport());
  }
}
