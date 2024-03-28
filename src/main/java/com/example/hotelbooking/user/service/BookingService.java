package com.example.hotelbooking.user.service;

import com.example.hotelbooking.user.model.dto.booking.BookingNewDto;
import com.example.hotelbooking.user.model.dto.booking.BookingResponseDto;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    List<BookingResponseDto> sendAllUserAccountsBookingList(Long hotelId,
                                                            Long roomId,
                                                            LocalDate start,
                                                            LocalDate end,
                                                            PageRequest page);


    BookingResponseDto createCheckIn(Long hotelId,
                                     Long roomId,
                                     Long userId,
                                     BookingNewDto newCheckIn);
}
