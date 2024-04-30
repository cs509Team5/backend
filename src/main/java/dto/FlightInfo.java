package dto;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightInfo {
  Timestamp departDatetime;
  Timestamp arriveDatetime;
  String departAirport;
  String arriveAirport;
  String flightNumber;
  String seatClass;
  String flightType;
  String isLayover;
  Double price;
}
