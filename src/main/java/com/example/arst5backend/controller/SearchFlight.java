package com.example.arst5backend.controller;

import com.example.arst5backend.service.search.ISearchStrategy;
import dto.FlightInfo;
import dto.SearchRequest;
import dto.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*")
public class SearchFlight {

    private final ISearchStrategy searchStrategy;

    @Autowired
    public SearchFlight(ISearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    @PostMapping
    public SearchResponse search(@RequestBody SearchRequest searchRequest) {

        List<FlightInfo> allDepartureFlights = searchStrategy.evaluate(
          searchRequest.getDepartureAirport(),
          searchRequest.getArrivalAirport(),
          searchRequest.getNumberOfStopover(),
          searchRequest.isAcceptEconomy(),
          searchRequest.isAcceptFirstClass(),
          searchRequest.getDepartureDate());

        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setDepartureFlights(allDepartureFlights);

        if (searchRequest.getReturnDate() != null) {
            List<FlightInfo> allReturnFlights = searchStrategy.evaluate(
              searchRequest.getArrivalAirport(),
              searchRequest.getDepartureAirport(),
              searchRequest.getNumberOfStopover(),
              searchRequest.isAcceptEconomy(),
              searchRequest.isAcceptFirstClass(),
              searchRequest.getReturnDate());
            searchResponse.setReturnFlights(allReturnFlights);
        }
        return searchResponse;
    }
}
