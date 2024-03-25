package com.example.hotelbooking.user.service;

import com.example.hotelbooking.exception.exceptions.ObjectNotFoundException;
import com.example.hotelbooking.hotel.model.entity.Hotel;
import com.example.hotelbooking.hotel.model.entity.Room;
import com.example.hotelbooking.hotel.repository.HotelRepository;
import com.example.hotelbooking.hotel.repository.RoomRepository;
import com.example.hotelbooking.user.model.dto.booking.BookingNewDto;
import com.example.hotelbooking.user.model.dto.booking.BookingResponseDto;
import com.example.hotelbooking.user.model.entity.User;
import com.example.hotelbooking.user.repository.BookingRepository;
import com.example.hotelbooking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    private final HotelRepository hotelRepository;

    private final RoomRepository roomRepository;

    @Override
    public List<BookingResponseDto> sendAllUserAccountsBookingList(Long hotelId,
                                                                   Long roomId,
                                                                   PageRequest page) {

        return null;
    }

    @Override
    public BookingResponseDto createCheckIn(Long hotelId,
                                            Long roomId,
                                            BookingNewDto newCheckIn) {

        return null;
    }

    private Room checkRoomInDb(Long roomId) {
        log.warn("No Hotel for update");
        return roomRepository.findById(roomId).orElseThrow(() ->
                new ObjectNotFoundException("Room not present!"));
    }

    private Hotel checkHotelInDb(Long hotelId) {
        log.warn("No Hotel for update");
        return hotelRepository.findById(hotelId).orElseThrow(() ->
                new ObjectNotFoundException("Hotel not present!"));
    }

    private User checkUserInDb(Long userID) {
        log.warn("No Hotel for update");
        return userRepository.findById(userID).orElseThrow(() ->
                new ObjectNotFoundException("User was not present"));
    }
}
