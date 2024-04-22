package com.example.arst5backend.service.search;

import com.example.arst5backend.model.airlines.FlightCapacity;
import dto.FlightInfo;
import org.springframework.stereotype.Service;

@Service
public class SetFlight implements ISetFlight{

  @Override
  public FlightInfo set(
    FlightCapacity flightResult,
    FlightInfo flight)
  {
    flight.setArriveAirport(flightResult.getArriveairport());
    flight.setDepartAirport(flightResult.getDepartairport());
    flight.setDepartDatetime(flightResult.getDepartdatetime());
    flight.setArriveDatetime(flightResult.getArrivedatetime());
    flight.setFlightNumber(flightResult.getFlightnumber());
    flight.setFlightType(flightResult.getFlighttype());
    return flight;
  }
}
