package com.example.arst5backend.service.airlines;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.FlightsReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SearchTicketsStopoverArrive implements ISearchTickets{
  private final FlightsReserveRepository flightsReserveRepository;

  @Autowired
  public SearchTicketsStopoverArrive(FlightsReserveRepository flightsReserveRepository) {
    this.flightsReserveRepository = flightsReserveRepository;
  }
  @Override
  public List<FlightCapacity> searchTickets(
    String DepartAirport,
    String ArriveAirport,
    Timestamp MinDepartDateTime,
    Timestamp MaxDepartDateTime
  ) {
    return flightsReserveRepository.findByArriveairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
      ArriveAirport,
      MinDepartDateTime,
      MaxDepartDateTime
    );
  }
}
