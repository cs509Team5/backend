package com.example.arst5backend.repository.airlines;

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
    FlightCapacity saveAndFlush(FlightCapacity flightCapacity);
}