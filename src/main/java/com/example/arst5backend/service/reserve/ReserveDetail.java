package com.example.arst5backend.service.reserve;

import com.example.arst5backend.model.airlines.FlightCapacity;
import org.springframework.stereotype.Service;

@Service
public class ReserveDetail implements IReserveDetail{

  @Override
  public String detail(FlightCapacity tmp) {
    String book = "You have successfully reserved a flight ticket." +
      "\nYour flight ticket information is as follows: " +
      "\nDeparture Airport: " + tmp.getDepartairport() +
      "\nArrival Airport: " + tmp.getArriveairport() +
      "\nDeparture Time: " + tmp.getDepartdatetime() +
      "\nArrival Time: " + tmp.getArrivedatetime() +
      "\nFlight Number: " + tmp.getFlightnumber() ;
    return book;
  }
}
