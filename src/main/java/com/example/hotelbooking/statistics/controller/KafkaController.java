package com.example.hotelbooking.statistics.controller;

import com.example.hotelbooking.statistics.model.KafkaMessage;
import com.example.hotelbooking.statistics.repository.BookingStatisticsRepository;
import com.example.hotelbooking.statistics.repository.UserStatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hotel-booking/kafka-test")
@RequiredArgsConstructor
public class KafkaController {

//    @Value("${app.kafka.topicToRead}")
//    private String topic1;
//
//    @Value("${app.kafka.topicToWrite}")
//    private String topic2;

//    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    private final UserStatisticsRepository userStatisticsRepository;

    private final BookingStatisticsRepository bookingStatisticsRepository;

//    @PostMapping("/send/user")
//    @ResponseStatus(HttpStatus.OK)
//    public String sendUserStatistics(@RequestBody KafkaMessage message) {
//
//        log.info("User message send to kafka");
//
//        topic1 = message.getType();
//        kafkaTemplate.send(topic1, message);
//
//        return "Message were send to kafka";
//    }
//
//    @PostMapping("/send/booking")
//    @ResponseStatus(HttpStatus.OK)
//    public String sendBookingStatistics(@RequestBody KafkaMessage message) {
//
//        log.info("Booking message was send to kafka");
//
//        topic2 = message.getType();
//        kafkaTemplate.send(topic2, message);
//
//        return "Message were send to kafka";
//    }

    @GetMapping("/sendUser")
    @ResponseStatus(HttpStatus.OK)
    public String getUserStatus() {

        return userStatisticsRepository.findAll().toString();
    }

    @GetMapping("/senBooking")
    @ResponseStatus(HttpStatus.OK)
    public String getBookingStatus() {

        return bookingStatisticsRepository.findAll().toString();
    }
}
