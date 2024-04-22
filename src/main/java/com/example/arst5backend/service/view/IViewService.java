package com.example.arst5backend.service.view;

import dto.FlightInfo;

import java.util.Date;

public interface IViewService {
  FlightInfo viewDetail(
    String departAirport,
    Date departDate,
    String flightNumber);
}
