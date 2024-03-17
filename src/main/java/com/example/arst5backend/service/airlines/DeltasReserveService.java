package com.example.arst5backend.service.airlines;

import com.example.arst5backend.model.airlines.Deltas;
import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.DeltasRepository;
import com.example.arst5backend.repository.airlines.DeltasReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DeltasReserveService {
    private final DeltasReserveRepository deltasReserveRepository;

    @Autowired
    public DeltasReserveService(DeltasReserveRepository deltasReserveRepository) {
        this.deltasReserveRepository = deltasReserveRepository;
    }
    public List<FlightCapacity> searchDeltas(
            String DepartAirport,
            String ArriveAirport,
            String FlightNumber,
            Timestamp MinDepartDateTime,
            Timestamp MaxDepartDateTime
    ) {
        return deltasReserveRepository.findByDepartairportAndArriveairportAndFlightnumberAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
                DepartAirport,
                ArriveAirport,
                FlightNumber,
                MinDepartDateTime,
                MaxDepartDateTime
        );
    }
}
