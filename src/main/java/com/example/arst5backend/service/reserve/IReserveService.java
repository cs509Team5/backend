package com.example.arst5backend.service.reserve;

import java.sql.Date;

public interface IReserveService {
  String reserveFlights(
    String DepartAirport,
    String ArriveAirport,
    String FlightNumber,
    String SeatType,
    Date DepartDate
  );
}
