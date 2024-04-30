package com.example.arst5backend.service.search;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.service.airlines.ISearchTickets;
import dto.FlightInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SearchNonStopoverService implements ISearchService {
  private final ISearchTickets searchTickets;

  @Autowired
  public SearchNonStopoverService(ISearchTickets searchTickets) {
    this.searchTickets = searchTickets;
  }

  @Override
  public List<FlightInfo> searchFlights(
      String DepartAirport,
      String ArriveAirport,
      int numberOfStopover,
      Boolean AcceptEconomy,
      Boolean AcceptFirstClass,
      Date DepartDate) {
    // Get a Calendar instance and set the time to DepartDate
    Calendar departCalendarMin = Calendar.getInstance();
    departCalendarMin.setTime(DepartDate);

    // Reset hour, minute, second, and millisecond
    departCalendarMin.set(Calendar.HOUR_OF_DAY, 0);
    departCalendarMin.set(Calendar.MINUTE, 0);
    departCalendarMin.set(Calendar.SECOND, 0);
    departCalendarMin.set(Calendar.MILLISECOND, 0);

    Timestamp departure_min_timestamp = new Timestamp(departCalendarMin.getTimeInMillis());
    // Construct the max time stamp.
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(DepartDate);
    calendar.add(Calendar.DAY_OF_MONTH, 1);
    Timestamp departure_max_timestamp = new Timestamp(calendar.getTimeInMillis());
    List<FlightInfo> flights = new ArrayList<>();
    List<FlightCapacity> flightResults = searchTickets.searchTickets(
        DepartAirport,
        ArriveAirport,
        departure_min_timestamp,
        departure_max_timestamp);
    for (FlightCapacity flightResult : flightResults) {
      FlightInfo flight = new FlightInfo();
      int economyclassnum = flightResult.getEconomyclassnum();
      int firstclassnum = flightResult.getFirstclassnum();
      if (AcceptEconomy == true && AcceptFirstClass == true) {
        if (economyclassnum > 0 && firstclassnum > 0) {
          flight.setSeatClass("Economy and first class options available");
          flight.setArriveAirport(flightResult.getArriveairport());
          flight.setDepartAirport(flightResult.getDepartairport());
          flight.setDepartDatetime(flightResult.getDepartdatetime());
          flight.setArriveDatetime(flightResult.getArrivedatetime());
          flight.setFlightNumber(flightResult.getFlightnumber());
          flight.setFlightType(flightResult.getFlighttype());
          flight.setIsLayover("Direct Flight");
          flight.setPrice(flightResult.getPrice());
          flights.add(flight);
        } else if (economyclassnum > 0 && firstclassnum == 0) {
          flight.setSeatClass("Only economy class available");
          flight.setArriveAirport(flightResult.getArriveairport());
          flight.setDepartAirport(flightResult.getDepartairport());
          flight.setDepartDatetime(flightResult.getDepartdatetime());
          flight.setArriveDatetime(flightResult.getArrivedatetime());
          flight.setFlightNumber(flightResult.getFlightnumber());
          flight.setFlightType(flightResult.getFlighttype());
          flight.setIsLayover("Direct Flight");
          flight.setPrice(flightResult.getPrice());
          flights.add(flight);
        } else if (economyclassnum == 0 && firstclassnum > 0) {
          flight.setSeatClass("Only first class available");
          flight.setArriveAirport(flightResult.getArriveairport());
          flight.setDepartAirport(flightResult.getDepartairport());
          flight.setDepartDatetime(flightResult.getDepartdatetime());
          flight.setArriveDatetime(flightResult.getArrivedatetime());
          flight.setFlightNumber(flightResult.getFlightnumber());
          flight.setFlightType(flightResult.getFlighttype());
          flight.setIsLayover("Direct Flight");
          flight.setPrice(flightResult.getPrice());
          flights.add(flight);
        }
      } else if (AcceptEconomy == true && AcceptFirstClass == false) {
        if (economyclassnum > 0) {
          flight.setSeatClass("Economy Class");
          flight.setArriveAirport(flightResult.getArriveairport());
          flight.setDepartAirport(flightResult.getDepartairport());
          flight.setDepartDatetime(flightResult.getDepartdatetime());
          flight.setArriveDatetime(flightResult.getArrivedatetime());
          flight.setFlightNumber(flightResult.getFlightnumber());
          flight.setFlightType(flightResult.getFlighttype());
          flight.setIsLayover("Direct Flight");
          flight.setPrice(flightResult.getPrice());
          flights.add(flight);
        }
      } else if (AcceptEconomy == false && AcceptFirstClass == true) {
        if (firstclassnum > 0) {
          flight.setSeatClass("First Class");
          flight.setArriveAirport(flightResult.getArriveairport());
          flight.setDepartAirport(flightResult.getDepartairport());
          flight.setDepartDatetime(flightResult.getDepartdatetime());
          flight.setArriveDatetime(flightResult.getArrivedatetime());
          flight.setFlightNumber(flightResult.getFlightnumber());
          flight.setFlightType(flightResult.getFlighttype());
          flight.setIsLayover("Direct Flight");
          flight.setPrice(flightResult.getPrice());
          flights.add(flight);
        }
      }
    }
    return flights;
  }
}
