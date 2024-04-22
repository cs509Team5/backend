package com.example.arst5backend.service.airlines;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.DeltasReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SearchTicketsStopoverArrive implements ISearchTicketsStopoverArrive{
  private final DeltasReserveRepository deltasReserveRepository;

  @Autowired
  public SearchTicketsStopoverArrive(DeltasReserveRepository deltasReserveRepository) {
    this.deltasReserveRepository = deltasReserveRepository;
  }

  @Override
  public List<FlightCapacity> searchTicketsArrive(
    String ArriveAirport,
    Timestamp MinDepartDateTime,
    Timestamp MaxDepartDateTime
  ) {
    return deltasReserveRepository.findByArriveairportAndDepartdatetimeGreaterThanEqualAndDepartdatetimeLessThan(
      ArriveAirport,
      MinDepartDateTime,
      MaxDepartDateTime
    );
  }
}
