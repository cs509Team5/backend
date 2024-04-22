package com.example.arst5backend.service.search;

import dto.FlightInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;
@Service
public class SearchStrategy implements ISearchStrategy {
    private final ISearchService searchService;
    public SearchStrategy(ISearchService searchService) {
        this.searchService = searchService;
    }
    @Override
    public List<FlightInfo> evaluate(
      String DepartAirport,
      String ArriveAirport,
      int numberOfStopover,
      boolean AcceptEconomy,
      boolean AcceptFirstClass,
      Date DepartDate
    ) {
        List<FlightInfo> flights;
        List<Integer> my_list = new ArrayList<>(Arrays.asList(0, 0));
        if (numberOfStopover == 0) {
            flights = searchService.searchFlights(DepartAirport, ArriveAirport, my_list, AcceptEconomy, AcceptFirstClass, DepartDate);
        } else {
            my_list.set(1, 1);
            flights = searchService.searchFlights(DepartAirport, ArriveAirport, my_list, AcceptEconomy, AcceptFirstClass, DepartDate);
        }
        return flights;
    }
}
