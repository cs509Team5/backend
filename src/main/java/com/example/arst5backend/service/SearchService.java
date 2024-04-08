package com.example.arst5backend.service;

import com.example.arst5backend.model.Flight;
import com.example.arst5backend.model.airlines.Deltas;
import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.service.airlines.DeltasService;
import dto.FlightInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SearchService {

    private final DeltasService deltasService;

    @Autowired
    public SearchService(DeltasService deltasService) {
        this.deltasService = deltasService;
    }

    public List<FlightInfo> searchFlights(
            String DepartAirport,
            String ArriveAirport,
            List<Integer> my_list,
            Boolean AcceptEconomy,
            Boolean AcceptFirstClass,
            Date DepartDate
    ) {
        List<FlightInfo> flights = new ArrayList<>();
        if (my_list.contains(0)) {
            Timestamp departure_min_timestamp = new Timestamp(DepartDate.getTime());
            // Construct the max time stamp.
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DepartDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Timestamp departure_max_timestamp = new Timestamp(calendar.getTimeInMillis());
            List<FlightCapacity> flightResults = deltasService.searchDeltas(
                    DepartAirport,
                    ArriveAirport,
                    departure_min_timestamp,
                    departure_max_timestamp
            );
            for (FlightCapacity flightResult: flightResults) {
                FlightInfo flight = new FlightInfo();
                int economyclassnum= flightResult.getEconomyclassnum();
                int firstclassnum= flightResult.getFirstclassnum();
                if (AcceptEconomy == true && AcceptFirstClass == true) {
                    if (economyclassnum > 0 && firstclassnum > 0) {
                        flight.setSeatClass("Economy and first class options available");
                        flight.setArriveairport(flightResult.getArriveairport());
                        flight.setDepartairport(flightResult.getDepartairport());
                        flight.setDepartdatetime(flightResult.getDepartdatetime());
                        flight.setArrivedatetime(flightResult.getArrivedatetime());
                        flight.setFlightnumber(flightResult.getFlightnumber());
                        flight.setFlightType(flightResult.getFlighttype());
                        flight.setIsLayover("Direct Flight");
                        flights.add(flight);
                    } else if (economyclassnum > 0 && firstclassnum == 0) {
                        flight.setSeatClass("Only economy class available");
                        flight.setArriveairport(flightResult.getArriveairport());
                        flight.setDepartairport(flightResult.getDepartairport());
                        flight.setDepartdatetime(flightResult.getDepartdatetime());
                        flight.setArrivedatetime(flightResult.getArrivedatetime());
                        flight.setFlightnumber(flightResult.getFlightnumber());
                        flight.setFlightType(flightResult.getFlighttype());
                        flight.setIsLayover("Direct Flight");
                        flights.add(flight);
                    } else if (economyclassnum == 0 && firstclassnum > 0) {
                        flight.setSeatClass("Only first class available");
                        flight.setArriveairport(flightResult.getArriveairport());
                        flight.setDepartairport(flightResult.getDepartairport());
                        flight.setDepartdatetime(flightResult.getDepartdatetime());
                        flight.setArrivedatetime(flightResult.getArrivedatetime());
                        flight.setFlightnumber(flightResult.getFlightnumber());
                        flight.setFlightType(flightResult.getFlighttype());
                        flight.setIsLayover("Direct Flight");
                        flights.add(flight);
                    }
                } else if (AcceptEconomy == true && AcceptFirstClass == false) {
                    if (economyclassnum>0) {
                        flight.setSeatClass("Economy Class");
                        flight.setArriveairport(flightResult.getArriveairport());
                        flight.setDepartairport(flightResult.getDepartairport());
                        flight.setDepartdatetime(flightResult.getDepartdatetime());
                        flight.setArrivedatetime(flightResult.getArrivedatetime());
                        flight.setFlightnumber(flightResult.getFlightnumber());
                        flight.setFlightType(flightResult.getFlighttype());
                        flight.setIsLayover("Direct Flight");
                        flights.add(flight);
                    }
                } else if (AcceptEconomy == false && AcceptFirstClass == true) {
                    if (firstclassnum>0) {
                        flight.setSeatClass("First Class");
                        flight.setArriveairport(flightResult.getArriveairport());
                        flight.setDepartairport(flightResult.getDepartairport());
                        flight.setDepartdatetime(flightResult.getDepartdatetime());
                        flight.setArrivedatetime(flightResult.getArrivedatetime());
                        flight.setFlightnumber(flightResult.getFlightnumber());
                        flight.setFlightType(flightResult.getFlighttype());
                        flight.setIsLayover("Direct Flight");
                        flights.add(flight);
                    }
                }
            }
        }

        if (my_list.contains(1)) {
            Timestamp departure_min_timestamp = new Timestamp(DepartDate.getTime());
            // Construct the max time stamp.
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DepartDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            
            Timestamp departure_max_timestamp = new Timestamp(calendar.getTimeInMillis());
            List<FlightCapacity> flightResults1 = deltasService.searchDeltas1(
                    DepartAirport,
                    departure_min_timestamp,
                    departure_max_timestamp
            );
            //the second flight
            //assume the minimum stopover time is 45 min.
            //and the maximum stopover time is 24 hours.
            calendar.add(Calendar.MINUTE, 45);
            departure_min_timestamp = new Timestamp(calendar.getTimeInMillis());
            calendar.add(Calendar.SECOND, 140700);//57000+23hr+15min, the longest flight duration is 57000
            departure_max_timestamp = new Timestamp(calendar.getTimeInMillis());
            List<FlightCapacity> flightResults2 = deltasService.searchDeltas2(
                    ArriveAirport,
                    departure_min_timestamp,
                    departure_max_timestamp
            );

            for (FlightCapacity flightResult1: flightResults1) {
                Timestamp arriveTime1 = flightResult1.getArrivedatetime();
                int economyclassnum1 = flightResult1.getEconomyclassnum();
                int firstclassnum1 = flightResult1.getFirstclassnum();
                for (FlightCapacity flightResult2: flightResults2) {
                    int economyclassnum2 = flightResult2.getEconomyclassnum();
                    int firstclassnum2 = flightResult2.getFirstclassnum();
                    Timestamp departTime2 = flightResult2.getDepartdatetime();
                    long milliseconds = departTime2.getTime() - arriveTime1.getTime();
                    long seconds = milliseconds / 1000;
                    if (flightResult1.getArriveairport().equals(flightResult2.getDepartairport()) && seconds >= 2700 && seconds<= 86400 ) {
                        FlightInfo flight1 = new FlightInfo();
                        FlightInfo flight2 = new FlightInfo();
                        if (AcceptEconomy == true && AcceptFirstClass == true) {
                            if ((economyclassnum1 > 0 || firstclassnum1 > 0) && (economyclassnum2 > 0 || firstclassnum2 > 0)) {
                                flight1.setArriveairport(flightResult1.getArriveairport());
                                flight1.setDepartairport(flightResult1.getDepartairport());
                                flight1.setDepartdatetime(flightResult1.getDepartdatetime());
                                flight1.setArrivedatetime(flightResult1.getArrivedatetime());
                                flight1.setFlightnumber(flightResult1.getFlightnumber());
                                if (economyclassnum1 > 0 && firstclassnum1 > 0) {
                                    flight1.setSeatClass("Economy and first class options available");
                                } else if (economyclassnum1 > 0 && firstclassnum1 == 0) {
                                    flight1.setSeatClass("Only economy class available");
                                } else if (economyclassnum1 == 0 && firstclassnum1 > 0) {
                                    flight1.setSeatClass("Only first class available");
                                }
                                flight1.setFlightType(flightResult1.getFlighttype());
                                flight1.setIsLayover("First Leg of a Connecting Flight");
                                flights.add(flight1);

                                flight2.setArriveairport(flightResult2.getArriveairport());
                                flight2.setDepartairport(flightResult2.getDepartairport());
                                flight2.setDepartdatetime(flightResult2.getDepartdatetime());
                                flight2.setArrivedatetime(flightResult2.getArrivedatetime());
                                flight2.setFlightnumber(flightResult2.getFlightnumber());
                                if (economyclassnum2 > 0 && firstclassnum2 > 0) {
                                    flight2.setSeatClass("Economy and first class options available");
                                } else if (economyclassnum2 > 0 && firstclassnum2 == 0) {
                                    flight2.setSeatClass("Only economy class available");
                                } else if (economyclassnum2 == 0 && firstclassnum2 > 0) {
                                    flight2.setSeatClass("Only first class available");
                                }
                                flight2.setFlightType(flightResult2.getFlighttype());
                                flight2.setIsLayover("Second Leg of a Connecting Flight");
                                flights.add(flight2);
                            }
                        } else if (AcceptEconomy == true && AcceptFirstClass == false) {
                            if (economyclassnum1 > 0  && economyclassnum2 > 0 ) {
                                flight1.setArriveairport(flightResult1.getArriveairport());
                                flight1.setDepartairport(flightResult1.getDepartairport());
                                flight1.setDepartdatetime(flightResult1.getDepartdatetime());
                                flight1.setArrivedatetime(flightResult1.getArrivedatetime());
                                flight1.setFlightnumber(flightResult1.getFlightnumber());
                                flight1.setSeatClass("Economy Class");
                                flight1.setFlightType(flightResult1.getFlighttype());
                                flight1.setIsLayover("First Leg of a Connecting Flight");
                                flights.add(flight1);

                                flight2.setArriveairport(flightResult2.getArriveairport());
                                flight2.setDepartairport(flightResult2.getDepartairport());
                                flight2.setDepartdatetime(flightResult2.getDepartdatetime());
                                flight2.setArrivedatetime(flightResult2.getArrivedatetime());
                                flight2.setFlightnumber(flightResult2.getFlightnumber());
                                flight2.setSeatClass("Economy Class");
                                flight2.setFlightType(flightResult2.getFlighttype());
                                flight2.setIsLayover("Second Leg of a Connecting Flight");
                                flights.add(flight2);
                            }
                        } else if (AcceptEconomy == false && AcceptFirstClass == true) {
                            if (firstclassnum1 > 0  && firstclassnum2 > 0 ) {
                                flight1.setArriveairport(flightResult1.getArriveairport());
                                flight1.setDepartairport(flightResult1.getDepartairport());
                                flight1.setDepartdatetime(flightResult1.getDepartdatetime());
                                flight1.setArrivedatetime(flightResult1.getArrivedatetime());
                                flight1.setFlightnumber(flightResult1.getFlightnumber());
                                flight1.setSeatClass("First Class");
                                flight1.setFlightType(flightResult1.getFlighttype());
                                flight1.setIsLayover("First Leg of a Connecting Flight");
                                flights.add(flight1);

                                flight2.setArriveairport(flightResult2.getArriveairport());
                                flight2.setDepartairport(flightResult2.getDepartairport());
                                flight2.setDepartdatetime(flightResult2.getDepartdatetime());
                                flight2.setArrivedatetime(flightResult2.getArrivedatetime());
                                flight2.setFlightnumber(flightResult2.getFlightnumber());
                                flight2.setSeatClass("First Class");
                                flight2.setFlightType(flightResult2.getFlighttype());
                                flight2.setIsLayover("Second Leg of a Connecting Flight");
                                flights.add(flight2);
                            }
                        }
                    }
                }
            }
        }
        return flights;
    }
}
