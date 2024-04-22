package dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

public class ViewResponseTest {

  @Test
  public void testNoArgsConstructor() {
    ViewResponse response = new ViewResponse();
    assertEquals(null, response.getFlightDetail(), "FlightDetail should initially be null.");
  }

  @Test
  public void testAllArgsConstructor() {
    FlightInfo mockFlightInfo = mock(FlightInfo.class);
    ViewResponse response = new ViewResponse(mockFlightInfo);
    assertEquals(mockFlightInfo, response.getFlightDetail(), "FlightDetail should match the one passed to constructor.");
  }

  @Test
  public void testSetterGetter() {
    FlightInfo mockFlightInfo = mock(FlightInfo.class);
    ViewResponse response = new ViewResponse();
    response.setFlightDetail(mockFlightInfo);
    assertEquals(mockFlightInfo, response.getFlightDetail(), "FlightDetail should be the one set.");
  }
}
