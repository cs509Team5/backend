package com.example.arst5backend.service.search;

import dto.FlightInfo;

import java.sql.Date;
import java.util.List;

public interface ISearchStrategy {

  List<FlightInfo> evaluate(
     String DepartAirport,
     String ArriveAirport,
     int numberOfStopover,
     boolean AcceptEconomy,
     boolean AcceptFirstClass,
     Date DepartDate);
}
