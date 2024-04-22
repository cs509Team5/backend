package com.example.arst5backend.service.reserve;

import com.example.arst5backend.model.airlines.FlightCapacity;
import com.example.arst5backend.repository.airlines.DeltasReserveRepository;
import com.example.arst5backend.service.airlines.FlightReserveService;
import com.example.arst5backend.service.airlines.IFlightReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Service
public class ReserveService implements IReserveService{
    private final IFlightReserveService flightReserveService;
    private final IReserveDetail reserveDetail;
    private final DeltasReserveRepository deltasReserveRepository;
    @Autowired
    public ReserveService(FlightReserveService flightReserveService,
                          DeltasReserveRepository deltasReserveRepository,
                          IReserveDetail reserveDetail) {
        this.flightReserveService = flightReserveService;
        this.deltasReserveRepository = deltasReserveRepository;
        this.reserveDetail = reserveDetail;
    }
    @Override
    @Transactional
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

        List<FlightCapacity> flightCapacity = flightReserveService.searchFlights(
                DepartAirport,
                ArriveAirport,
                FlightNumber,
                departure_min_timestamp,
                departure_max_timestamp
        );

        for (FlightCapacity tmp : flightCapacity) {
            int num;
            if (SeatType.equals("FirstClass")) {
                num = tmp.getFirstclassnum();
                if (num >= 1) {
                    tmp.setFirstclassnum(num - 1);
                    deltasReserveRepository.saveAndFlush(tmp);
                    bookingDetails = (reserveDetail.detail(tmp)  + "\nSeat Type: FirstClass");
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
                    bookingDetails = (reserveDetail.detail(tmp) + "\nSeat Type: EconomyClass");
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
