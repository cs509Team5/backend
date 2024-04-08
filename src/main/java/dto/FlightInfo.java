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
    Timestamp departdatetime;
    Timestamp arrivedatetime;
    String departairport;
    String arriveairport;
    String flightnumber;
    String seatClass;
    String flightType;
    String isLayover;
}
