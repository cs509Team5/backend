package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
public class ViewRequest {
    String departureAirport;
    String flightNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date departureDate;

    public ViewRequest(
            String departureAirport,
            String flightNumber,
            @JsonFormat(pattern="yyyy-MM-dd")
            Date departureDate
    ) {
        this.departureDate = departureDate;
        this.departureAirport = departureAirport;
        this.flightNumber = flightNumber;
    }
}
