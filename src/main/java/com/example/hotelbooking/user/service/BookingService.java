package com.example.hotelbooking.user.service;

import com.example.hotelbooking.user.model.dto.booking.BookingNewDto;
import com.example.hotelbooking.user.model.dto.booking.BookingResponseDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BookingService {

    List<BookingResponseDto> sendAllUserAccountsBookingList(Long hotelId,
                                                            Long roomId,
                                                            PageRequest page);


    BookingResponseDto createCheckIn(Long hotelId,
                                     Long roomId,
                                     BookingNewDto newCheckIn);
}
