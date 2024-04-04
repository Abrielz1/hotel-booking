package com.example.hotelbooking.statistics.service;

import com.example.hotelbooking.common.PrintToCVS;
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

    PrintToCVS printer = new PrintToCVS();

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

        int size = bookingStatisticsRepository.findBookingStatisticsByIdIsAndOut(in, out).size();
        return String.valueOf(size);
    }

    public void saveStatistics(String data) {

        printer.save(data);
    }

    public String printStatistics() {

        long userStatistics = userStatisticsRepository.findAll().size();

        long bookingStatistics = bookingStatisticsRepository.findAll().size();

        String data = userStatistics + "," + bookingStatistics;

        saveStatistics(data);

        return data;
    }
}
