package com.example.arst5backend.service.airlines;

import com.example.arst5backend.model.airlines.FlightCapacity;

import java.sql.Timestamp;
import java.util.List;

public interface ISearchTicketsStopoverDepart {
  List<FlightCapacity> searchTicketsDepart(
    String DepartAirport,
    Timestamp MinDepartDateTime,
    Timestamp MaxDepartDateTime
  );
}
