package com.example.hotelbooking.user.controller;

import com.example.hotelbooking.common.Create;
import com.example.hotelbooking.statistics.model.KafkaMessage;
import com.example.hotelbooking.statistics.service.KafkaMessageService;
import com.example.hotelbooking.user.enums.RoleType;
import com.example.hotelbooking.user.model.dto.user.UserNewDto;
import com.example.hotelbooking.user.model.dto.user.UserResponseDto;
import com.example.hotelbooking.user.model.entity.User;
import com.example.hotelbooking.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hotel-booking/auth")
@RequiredArgsConstructor
public class PublicController {

    @Value("${app.kafka.topicToRead}")
    private String topic1;

    private final KafkaMessageService kafkaMessageService;


    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto registerUserAccount(@Validated(Create.class)
                                               @RequestBody UserNewDto newUserAccount,
                                               @RequestParam(name = "role") RoleType role) {
        log.info("User Account was created via auth controller at" + " time: " + LocalDateTime.now());

        UserResponseDto userResponseDto = userService.registerUserAccount(newUserAccount, role);

        KafkaMessage message = new KafkaMessage();
        message.setType("user-statistics");
        message.setMessage(new ArrayList<>(List.of(userResponseDto.getId().toString())));
        kafkaMessageService.saveInDbUserStatistics(message);

        return userResponseDto;
    }
}
