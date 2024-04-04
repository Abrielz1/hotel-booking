package com.example.hotelbooking.statistics.service;

import com.example.hotelbooking.statistics.repository.BookingStatisticsRepository;
import com.example.hotelbooking.statistics.repository.UserStatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final BookingStatisticsRepository bookingStatisticsRepository;

    private final UserStatisticsRepository userStatisticsRepository;

    @Override
    public String sendUserStatistics() {

       long result = userStatisticsRepository.findAll().size();
        return String.valueOf(result);
    }

    @Override
    public String sendBookingStatisticsForAllTime() {

        long result = bookingStatisticsRepository.findAll().size();
        return String.valueOf(result);
    }

    @Override
    public String sendBookingStatisticsForTimePeriod(LocalDate in, LocalDate out) {

        bookingStatisticsRepository.findBookingStatisticsByIdIsAndOut(in, out);
        return null;
    }
}
