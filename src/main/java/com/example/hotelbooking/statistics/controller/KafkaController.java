package com.example.hotelbooking.statistics.controller;

import com.example.hotelbooking.statistics.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hotel-booking/statistics")
@RequiredArgsConstructor
public class KafkaController {

    @Value("${app.kafka.topicToWrite}")
    private String topic;

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    @PostMapping("/send/user")
    @ResponseStatus(HttpStatus.OK)
    public String sendUserStatistics(@RequestBody KafkaMessage message) {

        log.info("User message send to kafka");

        topic = message.getType();
        kafkaTemplate.send(topic, message);

        return "Message were send to kafka";
    }

    @PostMapping("/send/booking")
    @ResponseStatus(HttpStatus.OK)
    public String sendBookingStatistics(@RequestBody KafkaMessage message) {

        log.info("Booking message was send to kafka");

        topic = message.getType();
        kafkaTemplate.send(topic, message);

        return "Message were send to kafka";
    }
}
