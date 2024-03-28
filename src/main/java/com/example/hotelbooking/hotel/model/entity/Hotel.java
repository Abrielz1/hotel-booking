package com.example.hotelbooking.hotel.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "table")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_name", nullable = false)
    private String hotelName;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "hotel_address", nullable = false)
    private String hotelAddress;

    @Column(name = "distance-from_center", nullable = false)
    private Long distanceFromCenter;

    @Column(name = "private ", nullable = true)
    private Integer hotelValuation;

    @Column(name = "hotel_rating", nullable = true)
    private Integer hotelRating;
}
