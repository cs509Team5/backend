package com.example.arst5backend.service;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.DeltasReserveRepository;
import com.example.arst5backend.service.airlines.DeltasReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class ReserveService {
    private final DeltasReserveService deltasReserveService;
    private final DeltasReserveRepository deltasReserveRepository;
    @Autowired
    public ReserveService(DeltasReserveService deltasReserveService, DeltasReserveRepository deltasReserveRepository) {
        this.deltasReserveService = deltasReserveService;
        this.deltasReserveRepository = deltasReserveRepository;
    }
    public String reserveFlights(
            String DepartAirport,
            String ArriveAirport,
            String FlightNumber,
            String SeatType,
            Date DepartDate
    ) {
        Timestamp departure_min_timestamp = new Timestamp(DepartDate.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DepartDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Timestamp departure_max_timestamp = new Timestamp(calendar.getTimeInMillis());
        String bookingDetails;

        List<FlightCapacity> flightCapacity = deltasReserveService.searchDeltas(
                DepartAirport,
                ArriveAirport,
                FlightNumber,
                departure_min_timestamp,
                departure_max_timestamp
        );
        System.out.println(flightCapacity);

        for (FlightCapacity tmp : flightCapacity) {
            int num;

            if (SeatType.equals("FirstClass")) {
                num = tmp.getFirstclassnum();
                if (num >= 1) {
                    tmp.setFirstclassnum(num - 1);
                    deltasReserveRepository.saveAndFlush(tmp);
                    bookingDetails = ("You have successfully reserved a flight ticket." +
                            "\nYour flight ticket information is as follows: " +
                            "\nDeparture Airport: " + tmp.getDepartairport() +
                            "\nArrival Airport: " + tmp.getArriveairport() +
                            "\nDeparture Time: " + tmp.getDepartdatetime() +
                            "\nArrival Time: " + tmp.getArrivedatetime() +
                            "\nFlight Number: " + tmp.getFlightnumber() +
                            "\nSeat Type: FirstClass");
                    System.out.println(bookingDetails);
                    return bookingDetails;
                } else {
                    bookingDetails = "This ticket has been sold out!";
                    return bookingDetails;
                }
            } else if (SeatType.equals("EconomyClass")) {
                num = tmp.getEconomyclassnum();
                if (num >= 1) {
                    tmp.setEconomyclassnum(num - 1);
                    deltasReserveRepository.saveAndFlush(tmp);
                    bookingDetails = ("You have successfully reserved a flight ticket." +
                            "\nYour flight ticket information is as follows: " +
                            "\nDeparture Airport: " + tmp.getDepartairport() +
                            "\nArrival Airport: " + tmp.getArriveairport() +
                            "\nDeparture Time: " + tmp.getDepartdatetime() +
                            "\nArrival Time: " + tmp.getArrivedatetime() +
                            "\nFlight Number: " + tmp.getFlightnumber() +
                            "\nSeat Type: EconomyClass");
                    System.out.println(bookingDetails);
                    return bookingDetails;
                } else {
                    bookingDetails = "This ticket has been sold out!";
                    return bookingDetails;
                }
            } else {
                bookingDetails = "Your seat type is wrong!";
                return bookingDetails;
            }
        }
        bookingDetails = "This ticket information doesn't exist in database!";
        return bookingDetails;
    }
}