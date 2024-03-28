package com.example.hotelbooking.hotel.service;

import com.example.hotelbooking.hotel.model.dto.hotel.HotelNewDto;
import com.example.hotelbooking.hotel.model.dto.hotel.HotelResponseDto;
import org.springframework.data.domain.PageRequest;
import java.util.List;

public interface HotelService {

    List<HotelResponseDto> getListOfHotels(PageRequest page);

    HotelResponseDto getHotelByHotelId(Long hotelId);

    HotelResponseDto creatNewHotel(HotelNewDto newHotel);

    HotelResponseDto updateHotelInfo(Long hotelId, HotelNewDto hotelToUpdate);

    HotelResponseDto removeHotelByHotellId(Long hotelId);

    HotelResponseDto updateHotelRating(Long hotelId, HotelNewDto hotelToRatingUpdate);
}
