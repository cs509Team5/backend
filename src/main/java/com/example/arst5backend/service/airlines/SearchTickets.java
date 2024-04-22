package com.example.arst5backend.service.airlines;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.DeltasReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SearchTickets implements ISearchTickets {
    private final DeltasReserveRepository deltasReserveRepository;

    @Autowired
    public SearchTickets(DeltasReserveRepository deltasReserveRepository) {
        this.deltasReserveRepository = deltasReserveRepository;
    }
    @Override
    public List<FlightCapacity> searchTickets(
            String DepartAirport,
            String ArriveAirport,
            Timestamp MinDepartDateTime,
            Timestamp MaxDepartDateTime
    ) {
            return deltasReserveRepository.findByDepartairportAndArriveairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
                    DepartAirport,
                    ArriveAirport,
                    MinDepartDateTime,
                    MaxDepartDateTime
            );
    }
}
