package com.example.arst5backend.controller;

import com.example.arst5backend.service.SearchService;
import com.example.arst5backend.service.SearchStrategy;
import dto.FlightInfo;
import dto.SearchRequest;
import dto.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchFlight {
    private final SearchStrategy searchStrategy;

    @Autowired
    public SearchFlight(SearchStrategy searchStrategy){
        this.searchStrategy = searchStrategy;
    }

    @GetMapping
    public SearchResponse search(@RequestBody SearchRequest searchRequest) {
        System.out.println("Search request: " + searchRequest);
        List<FlightInfo> allDepartureFlights = searchStrategy.evaluate(
                searchRequest.getDepartureAirport(),
                searchRequest.getArrivalAirport(),
                searchRequest.getNumberOfStopover(),
                searchRequest.getDepartureDate()
        );
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setDepartureFlights(allDepartureFlights);
        if (searchRequest.getReturnDate() != null) {
//            System.out.println("number of stopover: " + searchRequest.getNumberOfStopover());
            List<FlightInfo> allReturnFlights = searchStrategy.evaluate(
                    searchRequest.getArrivalAirport(),
                    searchRequest.getDepartureAirport(),
                    searchRequest.getNumberOfStopover(),
                    searchRequest.getReturnDate()
            );
            searchResponse.setReturnFlights(allReturnFlights);
        }
        return searchResponse;
    }
}
