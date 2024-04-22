package dto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReserveResponseTest {

  @Test
  public void testGetSetBookingDetails() {
    ReserveResponse response = new ReserveResponse();

    response.setBookingDetails("Booking details");

    assertEquals("Booking details", response.getBookingDetails());
  }
}
