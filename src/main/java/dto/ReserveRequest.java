package dto;

import com.example.arst5backend.model.Trip;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveRequest {
    String departureAirport;
    String arrivalAirport;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date departureDate;
    String flightNumber;
    String seatType;
}


