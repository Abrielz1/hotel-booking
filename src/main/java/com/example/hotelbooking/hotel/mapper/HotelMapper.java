package com.example.hotelbooking.hotel.mapper;

import com.example.hotelbooking.hotel.model.dto.hotel.HotelNewDto;
import com.example.hotelbooking.hotel.model.dto.hotel.HotelResponseDto;
import com.example.hotelbooking.hotel.model.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {

    HotelMapper HOTEL_MAPPER = Mappers.getMapper(HotelMapper.class);

    Hotel toHotel(HotelNewDto newHotel);

    HotelResponseDto toHotelResponseDto(Hotel hotel);
}
