package com.example.hotelbooking.hotel.service;

import com.example.hotelbooking.hotel.model.dto.HotelNewDto;
import com.example.hotelbooking.hotel.model.dto.HotelResponseDto;
import org.springframework.data.domain.PageRequest;
import java.util.List;

public interface HotelService {

    List<HotelResponseDto> getListOfHotels(PageRequest page);

    HotelResponseDto getHotelById(Long hotelId);

    HotelResponseDto creatNewHotel(HotelNewDto newHotel);

    HotelResponseDto updateHotelInfo(Long hotelId, HotelNewDto hotelToUpdate);

    HotelResponseDto removeHotelById(Long hotelId);
}
