package com.example.arst5backend.service.search;

import com.example.arst5backend.model.airlines.FlightCapacity;
import dto.FlightInfo;

public interface ISetFlight {
  FlightInfo set(
    FlightCapacity flightResult,
    FlightInfo flight);
}
