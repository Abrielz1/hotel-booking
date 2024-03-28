package com.example.hotelbooking.hotel.controller;

import com.example.hotelbooking.common.Create;
import com.example.hotelbooking.common.Update;
import com.example.hotelbooking.hotel.model.dto.hotel.HotelNewDto;
import com.example.hotelbooking.hotel.model.dto.hotel.HotelResponseDto;
import com.example.hotelbooking.hotel.model.dto.room.RoomNewDto;
import com.example.hotelbooking.hotel.model.dto.room.RoomResponseDto;
import com.example.hotelbooking.hotel.service.RoomService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/hotel/booking")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomService roomService;

    // todo:
    //  создание комнаты;
    //  редактирование комнаты;
    //  удаление комнаты.

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<HotelResponseDto> getListOfAvailableRoomsToBook(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
//                                                  @Positive @RequestParam(defaultValue = "10") Integer size) {
//
//        log.info("\nList of hotels were sent via hotels controller at time: " + LocalDateTime.now() + "\n");
//        PageRequest page = PageRequest.of(from / size, size);
//
//        return roomService.getListOfAvailableRoomsToBook(page);
//    }

    @GetMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    public RoomResponseDto getRoomById(@Positive @PathVariable(name = "hotelId") Long hotelId,
                                        @Positive @RequestParam(name = "roomId") Long roomId) {

        log.info(("\nHotel with hotelId: %d which containing room with roomId: %d" +
                " was sent via rooms controller at time: ")
                .formatted(hotelId, roomId)
                + LocalDateTime.now() + "\n");

        return roomService.getRoomById(hotelId, roomId);
    }

    @PostMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomResponseDto creatNewRoomInHotel(@Positive @PathVariable(name = "hotelId") Long hotelId,
                                                @NotBlank @Validated(Create.class)
                                                @RequestBody RoomNewDto newRoomInHotel) {

        log.info("\nRoom in hotel with hotelId; %d was created via rooms controller at time: "
                .formatted(hotelId) + LocalDateTime.now() + "\n");

        return roomService.creatNewRoomInHotel(hotelId, newRoomInHotel);
    }

    @PutMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    public RoomResponseDto updateRoomInfoInHotel(@Positive @PathVariable(name = "hotelId") Long hotelId,
                                                  @Positive @RequestParam(name = "roomId") Long roomId,
                                                  @NotBlank @Validated(Update.class)
                                                  @RequestBody  RoomNewDto roomToUpdateInHotel) {

        log.info(("\nRoom with roomId: %d  in hotel with hotelId:" +
                " %d was updated via rooms controller at time: ").formatted(roomId, hotelId) +
                LocalDateTime.now() + "\n");

        return roomService.updateRoomInfoInHotel(hotelId, roomId, roomToUpdateInHotel);
    }

    @DeleteMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RoomResponseDto removeRoomInHotelByRoomId(@Positive @PathVariable(name = "hotelId") Long hotelId,
                                                     @Positive @RequestParam(name = "roomId") Long roomId) {

        log.info(("\nRoom with roomId: %d in hotel with hotelId: %d" +
                " was deleted via rooms controller at time: ").formatted(roomId, hotelId)
                + LocalDateTime.now() + "\n");

        return roomService.removeRoomInHotelByRoomId(hotelId, roomId);
    }

}
