package com.example.arst5backend.service.search;

import dto.FlightInfo;

import java.sql.Date;
import java.util.List;

public interface ISearchService {
  List<FlightInfo> searchFlights(
     String DepartAirport,
     String ArriveAirport,
     int numberOfStopover,
     Boolean AcceptEconomy,
     Boolean AcceptFirstClass,
     Date DepartDate
  );
}
