package com.example.arst5backend.service.airlines;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.FlightsReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FlightReserveService implements IFlightReserveService{
    private final FlightsReserveRepository flightsReserveRepository;

    @Autowired
    public FlightReserveService(FlightsReserveRepository flightsReserveRepository) {
        this.flightsReserveRepository = flightsReserveRepository;
    }
    @Override
    public List<FlightCapacity> searchFlights(
            String DepartAirport,
            String ArriveAirport,
            String FlightNumber,
            Timestamp MinDepartDateTime,
            Timestamp MaxDepartDateTime
    ) {
        return flightsReserveRepository.findByDepartairportAndArriveairportAndFlightnumberAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
                DepartAirport,
                ArriveAirport,
                FlightNumber,
                MinDepartDateTime,
                MaxDepartDateTime
        );
    }
}
