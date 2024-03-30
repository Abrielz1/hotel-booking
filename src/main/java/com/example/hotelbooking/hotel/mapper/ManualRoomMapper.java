package com.example.hotelbooking.hotel.mapper;

import com.example.hotelbooking.hotel.model.dto.room.RoomNewDto;
import com.example.hotelbooking.hotel.model.dto.room.RoomResponseDto;
import com.example.hotelbooking.hotel.model.entity.Room;

public class ManualRoomMapper {

    public static Room toRoom(RoomNewDto newRoom) {

        return Room.builder()
                .roomName(newRoom.getRoomName())
                .roomDescription(newRoom.getRoomDescription())
                .maximumRoomCapacity(newRoom.getMaximumRoomCapacity())
                .dateWhenRoomWillBeOccupied(newRoom.getDateWhenRoomWillBeOccupied())
                .dateWhenRoomWillBeAvailable(newRoom.getDateWhenRoomWillBeAvailable())
                .roomPrice(newRoom.getRoomPrice())
                .build();
    }

    public static RoomResponseDto toRoomResponseDto(Room roomResponse) {

        return RoomResponseDto.builder()
                .id(roomResponse.getId())
                .roomName(roomResponse.getRoomName())
                .roomDescription(roomResponse.getRoomDescription())
                .maximumRoomCapacity(roomResponse.getMaximumRoomCapacity())
                .dateAndWhenRoomWillBeOccupied(roomResponse.getDateWhenRoomWillBeOccupied())
                .dateAndWhenRoomWillBeAvailable(roomResponse.getDateWhenRoomWillBeAvailable())
                .roomPrice(roomResponse.getRoomPrice())
                .build();
    }
}
