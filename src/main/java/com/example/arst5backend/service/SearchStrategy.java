package com.example.arst5backend.service;

import dto.FlightInfo;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class SearchStrategy {
    private final SearchService searchService;
    public SearchStrategy(SearchService searchService) {
        this.searchService = searchService;
    }
    public List<FlightInfo> evaluate(
    String DepartAirport,
    String ArriveAirport,
    int numberOfStopover,
    Date DepartDate
    ) {
        List<FlightInfo> flights = new ArrayList<>();
        List<Integer> my_list = new ArrayList<>(Arrays.asList(0, 0));
        if (numberOfStopover == 0) {
            flights = searchService.searchFlights(DepartAirport, ArriveAirport, my_list, DepartDate);
        } else {
            my_list.set(1, 1);
            flights = searchService.searchFlights(DepartAirport, ArriveAirport, my_list, DepartDate);
        }
        return flights;
    }
}
