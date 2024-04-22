package com.example.arst5backend.controller;

import com.example.arst5backend.service.view.IViewService;
import dto.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping("/view")
public class ViewDetail {
    private final IViewService viewService;
    @Autowired
    public ViewDetail(IViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping
    public ViewResponse view(@RequestBody ViewRequest viewRequest) {
        System.out.println("View request: " + viewRequest);
        FlightInfo flightDetail = viewService.viewDetail(
                viewRequest.getDepartureAirport(),
                viewRequest.getDepartureDate(),
                viewRequest.getFlightNumber()
        );
        ViewResponse viewResponse = new ViewResponse();
        viewResponse.setFlightDetail(flightDetail);
        return viewResponse;
    }
}
