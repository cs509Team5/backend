package com.example.arst5backend.service.search;

import dto.FlightInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService implements ISearchService {
    private final ISearchStopoverService searchStopoverService;
    private final ISearchNonStopoverService searchNonStopoverService;
    @Autowired
    public SearchService(
      ISearchStopoverService searchStopoverService,
      ISearchNonStopoverService searchNonStopoverService)
    {
        this.searchStopoverService = searchStopoverService;
        this.searchNonStopoverService = searchNonStopoverService;
    }
    @Override
    public List<FlightInfo> searchFlights(
            String DepartAirport,
            String ArriveAirport,
            List<Integer> my_list,
            Boolean AcceptEconomy,
            Boolean AcceptFirstClass,
            Date DepartDate
    ) {
        List<FlightInfo> flights = new ArrayList<>();
        flights = searchNonStopoverService.searchDetail(
          DepartAirport,
          ArriveAirport,
          AcceptEconomy,
          AcceptFirstClass,
          DepartDate,
          flights);

        if (my_list.contains(1)) {
          List<FlightInfo> flights1 = searchStopoverService.searchDetail(
            DepartAirport,
            ArriveAirport,
            AcceptEconomy,
            AcceptFirstClass,
            DepartDate,
            flights);
            flights.addAll(flights1);
        }
    return flights;
    }
}
