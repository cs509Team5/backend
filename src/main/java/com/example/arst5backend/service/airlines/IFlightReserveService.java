package com.example.arst5backend.service.airlines;

import com.example.arst5backend.model.airlines.FlightCapacity;

import java.sql.Timestamp;
import java.util.List;

public interface IFlightReserveService {
  List<FlightCapacity> searchFlights(
    String DepartAirport,
    String ArriveAirport,
    String FlightNumber,
    Timestamp MinDepartDateTime,
    Timestamp MaxDepartDateTime
  );
}
