package com.example.hotelbooking.statistics.service;

import java.time.LocalDate;

public interface StatisticsService {

    String sendUserStatistics();

    String sendBookingStatisticsForAllTime();

    String sendBookingStatisticsForTimePeriod(LocalDate start, LocalDate end);
}
