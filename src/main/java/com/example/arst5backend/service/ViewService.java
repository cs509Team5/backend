package com.example.arst5backend.service;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.DeltasReserveRepository;
import dto.FlightInfo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Service
public class ViewService {
    private final DeltasReserveRepository deltasReserveRepository;

    public ViewService(DeltasReserveRepository deltasReserveRepository) {
        this.deltasReserveRepository = deltasReserveRepository;
    }
    public FlightInfo viewDetail(String departAirport, Date departDate, String flightNumber) {

        FlightInfo flight = new FlightInfo();
        Timestamp minDepartDateTime = new Timestamp(departDate.getTime());
        List<FlightCapacity> flightResult  = deltasReserveRepository.findByDepartairportAndFlightnumberAndDepartdatetimeGreaterThanEqual(
                departAirport,
                flightNumber,
                minDepartDateTime
        );
        for (FlightCapacity result: flightResult) {
            flight.setArriveairport(result.getArriveairport());
            flight.setDepartairport(result.getDepartairport());
            flight.setDepartdatetime(result.getDepartdatetime());
            flight.setArrivedatetime(result.getArrivedatetime());
            flight.setFlightnumber(result.getFlightnumber());
            flight.setFlightType(result.getFlighttype());
        }
        return flight;
    }
}
