package com.example.hotelbooking.hotel.model.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponseDto {

    private String hotelName;

    private String displayName;

    private String city;

    private String hotelAddress;

    private Long distanceFromCenter;

    private Integer hotelValuation;

    private Integer hotelRating;
}
