package com.example.hotelbooking.hotel.model.dto.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDto {

    private Long id;

    private String roomName;

    private String RoomDescription;

    private Short maximumRoomCapacity;

    private LocalDateTime DateAndTimeWhenRoomWillBeOccupied;

    private LocalDateTime DateAndTimeWhenRoomWillBeAvailable;

    private Integer roomPrice;
}
