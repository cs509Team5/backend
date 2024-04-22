package com.example.arst5backend.service.airlines;

import com.example.arst5backend.model.airlines.FlightCapacity;

import java.sql.Timestamp;
import java.util.List;

public interface ISearchTicketsStopoverArrive {
  List<FlightCapacity> searchTicketsArrive(
    String ArriveAirport,
    Timestamp MinDepartDateTime,
    Timestamp MaxDepartDateTime
  );
}
