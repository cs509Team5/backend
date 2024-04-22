package dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SearchResponseTest {

  @Test
  public void testDefaultConstructor() {
    SearchResponse response = new SearchResponse();

    assertTrue(response.isSuccess());
    assertNull(response.getDepartureFlights());
    assertNull(response.getReturnFlights());
  }

  @Test
  public void testParameterizedConstructor() {
    List<FlightInfo> departureFlights = new ArrayList<>();
    List<FlightInfo> returnFlights = new ArrayList<>();

    SearchResponse response = new SearchResponse(false, departureFlights, returnFlights);

    assertFalse(response.isSuccess());
    assertEquals(departureFlights, response.getDepartureFlights());
    assertEquals(returnFlights, response.getReturnFlights());
  }
}
