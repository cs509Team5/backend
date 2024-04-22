package com.example.arst5backend.service.search;

import dto.FlightInfo;

import java.sql.Date;
import java.util.List;

public interface ISearchService {
  List<FlightInfo> searchFlights(
     String DepartAirport,
     String ArriveAirport,
     List<Integer> my_list,
     Boolean AcceptEconomy,
     Boolean AcceptFirstClass,
     Date DepartDate
  );
}
