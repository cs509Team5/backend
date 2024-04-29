package com.example.arst5backend.service.search;

import dto.FlightInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchService implements ISearchService {
    private final ISearchService searchNonStopoverService;
    private final ISearchService searchStopoverService;
    @Autowired
    public SearchService(
      ISearchService searchNonStopoverService,
      ISearchService searchStopoverService
      )
    {

        this.searchNonStopoverService = searchNonStopoverService;
        this.searchStopoverService = searchStopoverService;
    }
    @Override
    public List<FlightInfo> searchFlights(
      String DepartAirport,
      String ArriveAirport,
      int numberOfStopover,
      Boolean AcceptEconomy,
      Boolean AcceptFirstClass,
      Date DepartDate) {
      List<FlightInfo> flights = new ArrayList<>();
      if (numberOfStopover == 0) {
        flights.addAll(searchNonStopoverService.searchFlights(
          DepartAirport,
          ArriveAirport,
          numberOfStopover,
          AcceptEconomy,
          AcceptFirstClass,
          DepartDate));
        } else {
        flights.addAll(searchNonStopoverService.searchFlights(
          DepartAirport,
          ArriveAirport,
          numberOfStopover,
          AcceptEconomy,
          AcceptFirstClass,
          DepartDate));
        flights.addAll(searchStopoverService.searchFlights(
          DepartAirport,
          ArriveAirport,
          numberOfStopover,
          AcceptEconomy,
          AcceptFirstClass,
          DepartDate));
        }
      return flights;
    }
}




