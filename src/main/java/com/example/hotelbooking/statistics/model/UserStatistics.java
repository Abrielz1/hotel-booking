package com.example.hotelbooking.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
public class UserStatistics {

    @Id
    String id;

    Long useId;
}
