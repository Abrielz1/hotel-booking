package com.example.hotelbooking.user.service;

import com.example.hotelbooking.exception.exceptions.BadRequestException;
import com.example.hotelbooking.exception.exceptions.ObjectNotFoundException;
import com.example.hotelbooking.hotel.model.dto.room.RoomResponseDto;
import com.example.hotelbooking.hotel.model.entity.Hotel;
import com.example.hotelbooking.hotel.model.entity.Room;
import com.example.hotelbooking.hotel.repository.HotelRepository;
import com.example.hotelbooking.hotel.repository.RoomRepository;
import com.example.hotelbooking.user.model.dto.booking.BookingNewDto;
import com.example.hotelbooking.user.model.dto.booking.BookingResponseDto;
import com.example.hotelbooking.user.model.entity.Booking;
import com.example.hotelbooking.user.model.entity.User;
import com.example.hotelbooking.user.repository.BookingRepository;
import com.example.hotelbooking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import static com.example.hotelbooking.hotel.mapper.RoomMapper.ROOM_MAPPER;
import static com.example.hotelbooking.user.mapper.BookingMapper.BOOKING_MAPPER;

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
                                                                   LocalDate start,
                                                                   LocalDate end,
                                                                   PageRequest page) {

        return bookingRepository.getAllRoomsInHotelWhichAreInUse(hotelId,
                                                                 roomId,
                                                                 start,
                                                                 end,
                                                                 page)
                .stream()
                .map(BOOKING_MAPPER::toBookingResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponseDto createCheckIn(Long hotelId,
                                            Long roomId,
                                            Long userId,
                                            BookingNewDto newCheckIn) {

        User user = checkUserInDb(userId);
        Hotel hotel = checkHotelInDb(hotelId);
        Room room = checkRoomInDb(hotelId, roomId);

        if (!this.checkBookingOnSelectedDate(hotelId, roomId, newCheckIn.getCheckInRoom())) {
            throw new BadRequestException("Room currently in use");
        }

        Booking newBook = new Booking();

        newBook.setUser(user);
        newBook.setRoom(room);
        user.setBookingList(List.of(newBook));
        bookingRepository.save(newBook);

        return BOOKING_MAPPER.toBookingResponseDto(newBook);
    }


    public List<RoomResponseDto> sendListOfFreeRoomsOfCertainHotel(Long hotelId, LocalDate date) {

        return checkRoomsAvailability(hotelId, date)
                .stream()
                .map(ROOM_MAPPER::toRoomResponseDto)
                .collect(Collectors.toList());
    }

//    public List<RoomResponseDto> sendListOfThatOccupiedRoomsOfCertainHotel(Long hotelId) {
//
//        LocalDate currentDate = LocalDate.now();
//
//        return checkRoomRoomsThatAreOccupied(hotelId, currentDate)
//                .stream()
//                .map(ROOM_MAPPER::toRoomResponseDto)
//                .collect(Collectors.toList());
//    }

    private Room checkRoomInDb(Long hotelId, Long roomId) {
        log.warn("No Room in selected hotel");
        return roomRepository.getRoom(hotelId, roomId).orElseThrow(() ->
                new ObjectNotFoundException("Room not present!"));
    }

    private Hotel checkHotelInDb(Long hotelId) {
        log.warn("No Hotel for update");
        return hotelRepository.findById(hotelId).orElseThrow(() ->
                new ObjectNotFoundException("Hotel not present!"));
    }

    private User checkUserInDb(Long userID) {
        log.warn("No User");
        return userRepository.findById(userID).orElseThrow(() ->
                new ObjectNotFoundException("User was not present"));
    }

    private List<Room> checkRoomsAvailability(Long hotelId, LocalDate currentDate) {
        return roomRepository.getListOfRoomAvailableOnCurrentDate(hotelId, currentDate);
    }

    private Boolean checkBookingOnSelectedDate(Long hotelId, Long roomId, LocalDateTime checkInRoom) {

        Room room = checkRoomInDb(hotelId, roomId);
        return checkInRoom.isBefore(room.getDateWhenRoomWillBeAvailable().atStartOfDay());
    }
}
