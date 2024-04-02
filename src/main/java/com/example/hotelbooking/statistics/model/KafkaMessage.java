package com.example.hotelbooking.statistics.model;

import lombok.Data;

@Data
public class KafkaMessage {

    private String type; // user or booking

    private String message;

    // В событии регистрации должны храниться данные о созданном пользователе (его ID).

    /*
В событии бронирования комнаты должны храниться данные о пользователе,
который оформил бронь (его ID), а также о датах заезда и выезда. Данные по этим
событиям нужно хранить в MongoDB в формате JSON.
   */

}
