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
public class DeltasService {
    private final DeltasReserveRepository deltasReserveRepository;

    @Autowired
    public DeltasService(DeltasReserveRepository deltasReserveRepository) {
        this.deltasReserveRepository = deltasReserveRepository;
    }

    public List<FlightCapacity> searchDeltas(
            String DepartAirport,
            String ArriveAirport,
            // Inclusive
            Timestamp MinDepartDateTime,
            // Exclusive
            Timestamp MaxDepartDateTime
    ) {
//        System.out.println(DepartAirport);
//        System.out.println(ArriveAirport);
//        System.out.println(MinDepartDateTime);
//        System.out.println(MaxDepartDateTime);
            return deltasReserveRepository.findByDepartairportAndArriveairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
                    DepartAirport,
                    ArriveAirport,
                    MinDepartDateTime,
                    MaxDepartDateTime
            );
    }

    public List<FlightCapacity> searchDeltas1(
            String DepartAirport,
            Timestamp MinDepartDateTime,
            // Exclusive
            Timestamp MaxDepartDateTime
    ) {
//        System.out.println(DepartAirport);
//        System.out.println(MinDepartDateTime);
//        System.out.println(MaxDepartDateTime);
        return deltasReserveRepository.findByDepartairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
                DepartAirport,
                MinDepartDateTime,
                MaxDepartDateTime
        );
    }
    public List<FlightCapacity> searchDeltas2(
            String ArriveAirport,
            Timestamp MinDepartDateTime,
            // Exclusive
            Timestamp MaxDepartDateTime
    ) {
//        System.out.println(ArriveAirport);
//        System.out.println(MinDepartDateTime);
//        System.out.println(MaxDepartDateTime);
        return deltasReserveRepository.findByArriveairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
                ArriveAirport,
                MinDepartDateTime,
                MaxDepartDateTime
        );
    }
}
