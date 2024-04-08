package dto;

import com.example.arst5backend.model.Airport;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    String departureAirport;
    String arrivalAirport;
    String flightNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date departureDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date returnDate;
    int numOfFlexibleDate;
    int numberOfStopover;//0 or 1
    boolean acceptFirstClass;
    boolean acceptEconomy;

    public SearchRequest(
            String departureAirport,
            String arrivalAirport,
            int numberOfStopover,
            boolean acceptEconomy,
            boolean acceptFirstClass,
            @JsonFormat(pattern="yyyy-MM-dd")
            Date departureDate
    ) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.numberOfStopover = numberOfStopover;
        this.acceptEconomy = acceptEconomy;
        this.acceptFirstClass = acceptFirstClass;
        this.departureDate = departureDate;
    }

    public SearchRequest(
            String departureAirport,
            String arrivalAirport,
            int numberOfStopover,
            boolean acceptEconomy,
            boolean acceptFirstClass,
            @JsonFormat(pattern="yyyy-MM-dd")
            Date departureDate,
            @JsonFormat(pattern="yyyy-MM-dd")
            Date returnDate
    ) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.numberOfStopover = numberOfStopover;
        this.acceptEconomy = acceptEconomy;
        this.acceptFirstClass = acceptFirstClass;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }
    // t

}
