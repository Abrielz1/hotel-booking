package com.example.hotelbooking.user.mapper;

import com.example.hotelbooking.user.model.dto.booking.BookingNewDto;
import com.example.hotelbooking.user.model.dto.booking.BookingResponseDto;
import com.example.hotelbooking.user.model.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    BookingMapper BOOKING_MAPPER = Mappers.getMapper(BookingMapper.class);

    Booking toBooking(BookingNewDto newBooking);

    BookingResponseDto toBookingResponseDto(Booking booking);
}
