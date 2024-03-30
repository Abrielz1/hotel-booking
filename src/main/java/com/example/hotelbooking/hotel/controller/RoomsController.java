package com.example.hotelbooking.hotel.controller;

import com.example.hotelbooking.common.Create;
import com.example.hotelbooking.common.Update;
import com.example.hotelbooking.hotel.model.dto.room.RoomNewDto;
import com.example.hotelbooking.hotel.model.dto.room.RoomResponseDto;
import com.example.hotelbooking.hotel.model.entity.RoomFilter;
import com.example.hotelbooking.hotel.service.RoomService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hotel-booking/hotel/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomService roomService;

    @GetMapping("/find-by-criteria/")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomResponseDto> findAllCriteria(
            @Positive @PathVariable(name = "hotelId") Long hotelId,
            @ModelAttribute RoomFilter filter,
            @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
            @Positive @RequestParam(defaultValue = "10") Integer size) {

        PageRequest page = PageRequest.of(from / size, size);

        return roomService.filteredByCriteria(hotelId, filter, page);
    }

    @GetMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public RoomResponseDto getRoomById(@Positive @PathVariable(name = "hotelId") Long hotelId,
                                       @Positive @PathVariable(name = "roomId") Long roomId) {

        log.info(("\nHotel with hotelId: %d which containing room with roomId: %d" +
                " was sent via rooms controller at time: ")
                .formatted(hotelId, roomId)
                + LocalDateTime.now() + "\n");

        return roomService.getRoomById(hotelId, roomId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RoomResponseDto creatNewRoomInHotel(@Positive @PathVariable(name = "hotelId") Long hotelId,
                                               @Validated(Create.class)
                                               @RequestBody RoomNewDto newRoomInHotel) {

        log.info("\nRoom in hotel with hotelId; %d was created via rooms controller at time: "
                .formatted(hotelId) + LocalDateTime.now() + "\n");

        return roomService.creatNewRoomInHotel(hotelId, newRoomInHotel);
    }

    @PutMapping("/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    public RoomResponseDto updateRoomInfoInHotel(@Positive @PathVariable(name = "hotelId") Long hotelId,
                                                 @Positive @PathVariable(name = "roomId") Long roomId,
                                                 @Validated(Update.class)
                                                 @RequestBody RoomNewDto roomToUpdateInHotel) {

        log.info(("\nRoom with roomId: %d  in hotel with hotelId:" +
                " %d was updated via rooms controller at time: ").formatted(roomId, hotelId) +
                LocalDateTime.now() + "\n");

        return roomService.updateRoomInfoInHotel(hotelId, roomId, roomToUpdateInHotel);
    }

    @DeleteMapping("/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RoomResponseDto removeRoomInHotelByRoomId(@Positive @PathVariable(name = "hotelId") Long hotelId,
                                                     @Positive @PathVariable(name = "roomId") Long roomId) {

        log.info(("\nRoom with roomId: %d in hotel with hotelId: %d" +
                " was deleted via rooms controller at time: ").formatted(roomId, hotelId)
                + LocalDateTime.now() + "\n");

        return roomService.removeRoomInHotelByRoomId(hotelId, roomId);
    }
}
