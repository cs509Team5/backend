package com.example.arst5backend.repository.airlines;

import com.example.arst5backend.model.airlines.Deltas;
import com.example.arst5backend.model.airlines.FlightCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DeltasReserveRepository extends JpaRepository<FlightCapacity, Long> {
    List<FlightCapacity> findByDepartairportAndArriveairportAndFlightnumberAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
            String DepartAirport,
            String ArriveAirport,
            String FlightNumber,
            Timestamp MinDepartDateTime,
            Timestamp MaxDepartDateTime
    );

    List<FlightCapacity> findByDepartairportAndArriveairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
            String DepartAirport,
            String ArriveAirport,
            Timestamp MinDepartDateTime,
            Timestamp MaxDepartDateTime
    );
    List<FlightCapacity> findByDepartairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
            String DepartAirport,
            Timestamp MinDepartDateTime,
            Timestamp MaxDepartDateTime
    );
    List<FlightCapacity> findByArriveairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
            String ArriveAirport,
            Timestamp MinDepartDateTime,
            Timestamp MaxDepartDateTime
    );
    List<FlightCapacity> findByDepartairportAndFlightnumberAndDepartdatetimeGreaterThanEqual(
            String DepartAirport,
            String FlightNumber,
            Timestamp MinDepartDateTime
            );
    FlightCapacity saveAndFlush(FlightCapacity flightCapacity);
}