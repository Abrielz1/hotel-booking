package com.example.hotelbooking.statistics.model;

import lombok.Data;

@Data
public class KafkaMessage {

    private String product;

    private Long quantity;
}
