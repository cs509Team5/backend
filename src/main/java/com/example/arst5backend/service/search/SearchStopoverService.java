package com.example.arst5backend.service.search;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.service.airlines.ISearchTickets;
import com.example.arst5backend.service.airlines.SearchTicketsStopoverArrive;
import com.example.arst5backend.service.airlines.SearchTicketsStopoverDepart;
import dto.FlightInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SearchStopoverService implements ISearchService {

  private final SearchTicketsStopoverDepart searchTicketsStopoverDepart;
  private final SearchTicketsStopoverArrive searchTicketsStopoverArrive;
  private final SetFlight setFlight;
  @Autowired
  public SearchStopoverService(
    SearchTicketsStopoverDepart searchTicketsStopoverDepart,
    SearchTicketsStopoverArrive searchTicketsStopoverArrive,
    SetFlight setFlight)
  {
    this.searchTicketsStopoverArrive = searchTicketsStopoverArrive;
    this.searchTicketsStopoverDepart = searchTicketsStopoverDepart;
    this.setFlight = setFlight;
  }

  @Override
  public List<FlightInfo> searchFlights(
    String DepartAirport,
    String ArriveAirport,
    int numberOfStopover,
    Boolean AcceptEconomy,
    Boolean AcceptFirstClass,
    Date DepartDate
  ) {
    Timestamp departure_min_timestamp = new Timestamp(DepartDate.getTime());
    // Construct the max time stamp.
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(DepartDate);
    calendar.add(Calendar.DAY_OF_MONTH, 1);

    Timestamp departure_max_timestamp = new Timestamp(calendar.getTimeInMillis());
    List<FlightInfo> flights = new ArrayList<>();

    List<FlightCapacity> flightResults1 = searchTicketsStopoverDepart.searchTickets(
      DepartAirport,
      ArriveAirport,
      departure_min_timestamp,
      departure_max_timestamp
    );
              /*the second flight
                assume the minimum stopover time is 45 min.
                and the maximum stopover time is 24 hours.*/
    calendar.add(Calendar.MINUTE, 45);
    departure_min_timestamp = new Timestamp(calendar.getTimeInMillis());
    calendar.add(Calendar.SECOND, 140700);//57000+23hr+15min, the longest flight duration is 57000
    departure_max_timestamp = new Timestamp(calendar.getTimeInMillis());

    List<FlightCapacity> flightResults2 = searchTicketsStopoverArrive.searchTickets(
      DepartAirport,
      ArriveAirport,
      departure_min_timestamp,
      departure_max_timestamp
    );
    for (FlightCapacity flightResult1: flightResults1) {
        Timestamp arriveTime1 = flightResult1.getArrivedatetime();
        int economyClassNum1 = flightResult1.getEconomyclassnum();
        int firstClassNum1 = flightResult1.getFirstclassnum();
        for (FlightCapacity flightResult2: flightResults2) {
          int economyClassNum2 = flightResult2.getEconomyclassnum();
          int firstClassNum2 = flightResult2.getFirstclassnum();
          Timestamp departTime2 = flightResult2.getDepartdatetime();
          long milliseconds = departTime2.getTime() - arriveTime1.getTime();
          long seconds = milliseconds / 1000;
          if (flightResult1.getArriveairport().equals(flightResult2.getDepartairport()) && seconds >= 2700 && seconds<= 86400 ) {
            FlightInfo flight1 = new FlightInfo();
            FlightInfo flight2 = new FlightInfo();
            if (AcceptEconomy == true && AcceptFirstClass == true) {
              if ((economyClassNum1 > 0 || firstClassNum1  > 0) && (economyClassNum2 > 0 || firstClassNum2 > 0)) {
                flight1 = setFlight.set(flightResult1, flight1);
                if (economyClassNum1 > 0 && firstClassNum1  > 0) {
                  flight1.setSeatClass("Economy and first class options available");
                } else if (economyClassNum1 > 0 && firstClassNum1  == 0) {
                  flight1.setSeatClass("Only economy class available");
                } else if (economyClassNum1 == 0 && firstClassNum1  > 0) {
                  flight1.setSeatClass("Only first class available");
                }
                flight1.setIsLayover("First Leg of a Connecting Flight");
                flights.add(flight1);

                flight2 = setFlight.set(flightResult2, flight2);
                if (economyClassNum2 > 0 && firstClassNum2 > 0) {
                  flight2.setSeatClass("Economy and first class options available");
                } else if (economyClassNum2 > 0 && firstClassNum2 == 0) {
                  flight2.setSeatClass("Only economy class available");
                } else if (economyClassNum2 == 0 && firstClassNum2 > 0) {
                  flight2.setSeatClass("Only first class available");
                }
                flight2.setIsLayover("Second Leg of a Connecting Flight");
                flights.add(flight2);
              }
            } else if (AcceptEconomy == true && AcceptFirstClass == false) {
              if (economyClassNum1 > 0  && economyClassNum2 > 0 ) {
                flight1 = setFlight.set(flightResult1, flight1);
                flight1.setSeatClass("Economy Class");
                flight1.setIsLayover("First Leg of a Connecting Flight");
                flights.add(flight1);

                flight2 = setFlight.set(flightResult2, flight2);
                flight2.setSeatClass("Economy Class");
                flight2.setIsLayover("Second Leg of a Connecting Flight");
                flights.add(flight2);
              }
            } else if (AcceptEconomy == false && AcceptFirstClass == true) {
              if (firstClassNum1  > 0  && firstClassNum2 > 0 ) {
                flight1 = setFlight.set(flightResult1, flight1);
                flight1.setSeatClass("First Class");
                flight1.setIsLayover("First Leg of a Connecting Flight");
                flights.add(flight1);

                flight2 = setFlight.set(flightResult2, flight2);
                flight2.setSeatClass("First Class");
                flight2.setIsLayover("Second Leg of a Connecting Flight");
                flights.add(flight2);
              }
           }
         }
      }
  }
    return flights;
  }
}
