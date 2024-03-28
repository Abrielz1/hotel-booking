package com.example.hotelbooking.hotel.service;

import com.example.hotelbooking.exception.exceptions.ObjectNotFoundException;
import com.example.hotelbooking.hotel.mapper.RoomMapper;
import com.example.hotelbooking.hotel.model.dto.room.RoomNewDto;
import com.example.hotelbooking.hotel.model.dto.room.RoomResponseDto;
import com.example.hotelbooking.hotel.model.entity.Hotel;
import com.example.hotelbooking.hotel.model.entity.Room;
import com.example.hotelbooking.hotel.repository.HotelRepository;
import com.example.hotelbooking.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final HotelRepository hotelRepository;

    private final RoomRepository roomRepository;


    @Override
    public RoomResponseDto getRoomById(Long hotelId, Long roomId) {

        Hotel hotel = checkHotelInDb(hotelId);

        Room room = checkRoomInDb(hotelId, roomId);

        if (!hotel.getListOfAvailableRoomsToBook().contains(room)) {
            throw new ObjectNotFoundException("There no room in hotel!");
        }

        log.info(("\nHotel with hotelId: %d which containing room with roomId: %d" +
                " was sent via rooms service at time: ")
                .formatted(hotelId, roomId)
                + LocalDateTime.now() + "\n");

        return RoomMapper.ROOM_MAPPER.toRoomResponseDto(room);
    }

    @Override
    public RoomResponseDto creatNewRoomInHotel(Long hotelId, RoomNewDto newRoomInHotel) {

        Hotel hotel = checkHotelInDb(hotelId);
        Room newRoom = new Room();
        newRoom.setHotel(hotel);
        hotel.setListOfAvailableRoomsToBook(List.of(newRoom));

        log.info("\nRoom in hotel with hotelId; %d was created via rooms service at time: "
                .formatted(hotelId) + LocalDateTime.now() + "\n");

        return RoomMapper.ROOM_MAPPER.toRoomResponseDto(
                roomRepository.save(RoomMapper.ROOM_MAPPER.toRoom(newRoomInHotel)));
    }

    @Override
    public RoomResponseDto updateRoomInfoInHotel(Long hotelId,
                                                 Long roomId,
                                                 RoomNewDto roomToUpdateInHotel) {

        Room room = checkRoomInDb(hotelId, roomId);
        Hotel hotel = checkHotelInDb(hotelId);

        if (roomToUpdateInHotel != null || hotel.getListOfAvailableRoomsToBook().contains(room)) {

            if (StringUtils.hasText(roomToUpdateInHotel.getRoomName())) {
                room.setRoomName(roomToUpdateInHotel.getRoomName());
            }

            if (StringUtils.hasText(roomToUpdateInHotel.getRoomDescription())) {
                room.setRoomDescription(roomToUpdateInHotel.getRoomDescription());
            }

            if (roomToUpdateInHotel.getMaximumRoomCapacity() != null) {
                room.setMaximumRoomCapacity(roomToUpdateInHotel.getMaximumRoomCapacity());
            }

            if (roomToUpdateInHotel.getDateAndTimeWhenRoomWillBeAvailable() != null &&
                    roomToUpdateInHotel.getDateAndTimeWhenRoomWillBeOccupied()
                            .isAfter(LocalDate.now()) &&
                    roomToUpdateInHotel.getDateAndTimeWhenRoomWillBeAvailable()
                            .isAfter(roomToUpdateInHotel.getDateAndTimeWhenRoomWillBeOccupied())) {
                room.setDateAndTimeWhenRoomWillBeAvailable(roomToUpdateInHotel.getDateAndTimeWhenRoomWillBeAvailable());
            }

            if (roomToUpdateInHotel.getDateAndTimeWhenRoomWillBeOccupied() != null &&
                    roomToUpdateInHotel.getDateAndTimeWhenRoomWillBeOccupied().isAfter(LocalDate.now()) &&
                    roomToUpdateInHotel.getDateAndTimeWhenRoomWillBeOccupied()
                            .isBefore(roomToUpdateInHotel.getDateAndTimeWhenRoomWillBeOccupied())) {
                room.setDateAndTimeWhenRoomWillBeOccupied(roomToUpdateInHotel.getDateAndTimeWhenRoomWillBeOccupied());
            }

            if (roomToUpdateInHotel.getRoomPrice() != null) {
                room.setRoomPrice(roomToUpdateInHotel.getRoomPrice());
            }

        } else {
            log.warn("No room fields for update");
            throw new ObjectNotFoundException("No room fields for update");
        }

        log.info(("\nRoom with roomId: %d  in hotel with hotelId:" +
                " %d was updated via rooms service at time: ").formatted(roomId, hotelId) +
                LocalDateTime.now() + "\n");

        return RoomMapper.ROOM_MAPPER.toRoomResponseDto(roomRepository.save(room));
    }

    @Override
    public RoomResponseDto removeRoomInHotelByRoomId(Long hotelId, Long roomId) {
        Room room = checkRoomInDb(hotelId, roomId);
        roomRepository.findById(roomId).ifPresent(roomRepository::delete);

        log.info(("\nRoom with roomId: %d in hotel with hotelId: %d" +
                " was deleted via rooms service at time: ").formatted(roomId, hotelId)
                + LocalDateTime.now() + "\n");

        return RoomMapper.ROOM_MAPPER.toRoomResponseDto(room);
    }

    private Room checkRoomInDb(Long hotelId, Long roomId) {
        log.warn("No Room in selected hotel");
        return roomRepository.getRoom(hotelId,roomId).orElseThrow(() ->
                new ObjectNotFoundException("Room not present!"));
    }

    private Hotel checkHotelInDb(Long hotelId) {
        log.warn("No Hotel for update");
        return hotelRepository.findById(hotelId).orElseThrow(() ->
                new ObjectNotFoundException("Hotel not present!"));
    }
}
