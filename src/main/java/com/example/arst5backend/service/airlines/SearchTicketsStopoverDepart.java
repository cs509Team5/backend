package com.example.arst5backend.service.airlines;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.DeltasReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SearchTicketsStopoverDepart implements  ISearchTicketsStopoverDepart{
  private final DeltasReserveRepository deltasReserveRepository;

  @Autowired
  public SearchTicketsStopoverDepart(DeltasReserveRepository deltasReserveRepository) {
    this.deltasReserveRepository = deltasReserveRepository;
  }
  @Override
  public List<FlightCapacity> searchTicketsDepart(
    String DepartAirport,
    Timestamp MinDepartDateTime,
    Timestamp MaxDepartDateTime
  ) {
    return deltasReserveRepository.findByDepartairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
      DepartAirport,
      MinDepartDateTime,
      MaxDepartDateTime
    );
  }
}
