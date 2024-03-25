package com.example.arst5backend.service;

import com.example.arst5backend.model.Flight;
import com.example.arst5backend.model.airlines.Deltas;
import com.example.arst5backend.service.airlines.DeltasService;
import dto.FlightInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
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

    // Simple search flights service.
    public List<FlightInfo> searchFlights(
            String DepartAirport,
            String ArriveAirport,
            List<Integer> my_list,
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
            List<Deltas> deltas = deltasService.searchDeltas(
                    DepartAirport,
                    ArriveAirport,
                    departure_min_timestamp,
                    departure_max_timestamp
            );
            for (Deltas delta: deltas) {
                FlightInfo flight = new FlightInfo();
                flight.setArriveairport(delta.getArriveairport());
                flight.setDepartairport(delta.getDepartairport());
                flight.setDepartdatetime(delta.getDepartdatetime());
                flight.setArrivedatetime(delta.getArrivedatetime());
                flight.setFlightnumber(delta.getFlightnumber());
                flights.add(flight);
            }
        }

        if (my_list.contains(1)) {
            Timestamp departure_min_timestamp = new Timestamp(DepartDate.getTime());
            // Construct the max time stamp.
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DepartDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Timestamp departure_max_timestamp = new Timestamp(calendar.getTimeInMillis());
            List<Deltas> deltas1 = deltasService.searchDeltas1(
                    DepartAirport,
                    departure_min_timestamp,
                    departure_max_timestamp
            );
            //the second flight
            //assume the minimum stopover time is 2 hours.
            //and the maximum stopover time is 24 hours.
            calendar.add(Calendar.HOUR_OF_DAY, 2);
            departure_min_timestamp = new Timestamp(calendar.getTimeInMillis());
            calendar.add(Calendar.SECOND, 136200);//57000+22hrs, the longest flight duration is 57000
            departure_max_timestamp = new Timestamp(calendar.getTimeInMillis());
            List<Deltas> deltas2 = deltasService.searchDeltas2(
                    ArriveAirport,
                    departure_min_timestamp,
                    departure_max_timestamp
            );
//            List<FlightInfo> flights = new ArrayList<FlightInfo>();
//            List<FlightInfo> flightInfoList = new ArrayList<>();
            for (Deltas delta1: deltas1) {
                Timestamp arriveTime = delta1.getArrivedatetime();
                for (Deltas delta2: deltas2) {
                    Timestamp departTime = delta2.getDepartdatetime();
                    long milliseconds = departTime.getTime() - arriveTime.getTime();
                    long seconds = milliseconds / 1000;
                    if (delta1.getArriveairport().equals(delta2.getDepartairport()) && seconds >=7200 && seconds<= 86400 ) {
                        FlightInfo flight1 = new FlightInfo();
                        flight1.setArriveairport(delta1.getArriveairport());
                        flight1.setDepartairport(delta1.getDepartairport());
                        flight1.setDepartdatetime(delta1.getDepartdatetime());
                        flight1.setArrivedatetime(delta1.getArrivedatetime());
                        flight1.setFlightnumber(delta1.getFlightnumber());
                        flights.add(flight1);

                        FlightInfo flight2 = new FlightInfo();
                        flight2.setArriveairport(delta2.getArriveairport());
                        flight2.setDepartairport(delta2.getDepartairport());
                        flight2.setDepartdatetime(delta2.getDepartdatetime());
                        flight2.setArrivedatetime(delta2.getArrivedatetime());
                        flight2.setFlightnumber(delta2.getFlightnumber());
                        flights.add(flight2);
                    }
                }
            }
        }
        return flights;
    }

    // TODO: More complex search flights service.
    public List<FlightInfo> searchFlights(
            String DepartAirport,
            String ArriveAirport,
            Date DepartDate,
            int NumberOfFlexibleDate,
            int NumberOfStopOver,
            Boolean AcceptFirstClass,
            Boolean AcceptEconomy
    ) {
        List<FlightInfo> flights = new ArrayList<>();
        return flights;
    }
}
