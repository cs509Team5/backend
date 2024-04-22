package com.example.arst5backend.service.view;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.DeltasReserveRepository;
import dto.FlightInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Service
public class ViewService implements IViewService {
    private final DeltasReserveRepository deltasReserveRepository;
    @Autowired
    public ViewService(DeltasReserveRepository deltasReserveRepository) {
        this.deltasReserveRepository = deltasReserveRepository;
    }
    @Override
    public FlightInfo viewDetail(String departAirport, Date departDate, String flightNumber) {

        FlightInfo flight = new FlightInfo();
        Timestamp minDepartDateTime = new Timestamp(departDate.getTime());
        List<FlightCapacity> flightResult  = deltasReserveRepository.findByDepartairportAndFlightnumberAndDepartdatetimeGreaterThanEqual(
                departAirport,
                flightNumber,
                minDepartDateTime
        );
        for (FlightCapacity result: flightResult) {
            flight.setArriveAirport(result.getArriveairport());
            flight.setDepartAirport(result.getDepartairport());
            flight.setDepartDatetime(result.getDepartdatetime());
            flight.setArriveDatetime(result.getArrivedatetime());
            flight.setFlightNumber(result.getFlightnumber());
            flight.setFlightType(result.getFlighttype());
        }
        return flight;
    }
}
