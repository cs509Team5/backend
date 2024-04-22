package com.example.arst5backend.service.search;

import dto.FlightInfo;

import java.sql.Date;
import java.util.List;

public interface ISearchNonStopoverService {
  List<FlightInfo> searchDetail(
    String DepartAirport,
    String ArriveAirport,
    Boolean AcceptEconomy,
    Boolean AcceptFirstClass,
    Date DepartDate,
    List<FlightInfo> flights);
}
