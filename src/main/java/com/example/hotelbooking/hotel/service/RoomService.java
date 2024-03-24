package com.example.hotelbooking.hotel.service;

import com.example.hotelbooking.hotel.model.dto.room.RoomNewDto;
import com.example.hotelbooking.hotel.model.dto.room.RoomResponseDto;

public interface RoomService {

    RoomResponseDto getRoomById(Long hotelId, Long roomId);

    RoomResponseDto creatNewRoomInHotel(Long hotelId, RoomNewDto newRoomInHotel);

    RoomResponseDto updateRoomInfoInHotel(Long hotelId, Long roomId, RoomNewDto roomToUpdateInHotel);

    RoomResponseDto removeRoomInHotelByRoomId(Long hotelId, Long roomId);
}
