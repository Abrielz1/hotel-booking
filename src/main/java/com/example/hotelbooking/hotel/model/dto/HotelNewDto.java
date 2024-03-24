package com.example.hotelbooking.hotel.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelNewDto {

    @NotBlank
    private String hotelName;

    @NotBlank
    private String displayName;

    @NotBlank
    private String city;

    @NotBlank
    private String hotelAddress;

    @NotBlank
    private Long distanceFromCenter;
}
