package com.example.hotelbooking.hotel.controller;

import com.example.hotelbooking.common.Create;
import com.example.hotelbooking.common.Update;
import com.example.hotelbooking.hotel.model.dto.hotel.HotelNewDto;
import com.example.hotelbooking.hotel.model.dto.hotel.HotelResponseDto;
import com.example.hotelbooking.hotel.service.HotelService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/hotel-booking/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HotelResponseDto> getListOfHotels(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                  @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("\nList of hotels were sent via hotels controller at time: " + LocalDateTime.now() + "\n");
        PageRequest page = PageRequest.of(from / size, size);

        return hotelService.getListOfHotels(page);
    }

    @GetMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    public HotelResponseDto getHotelById(@Positive @PathVariable(name = "hotelId") Long hotelId) {

        log.info("\nHotel with id: %d was sent via hotels controller at time: ".formatted(hotelId)
                + LocalDateTime.now() + "\n");

        return hotelService.getHotelByHotelId(hotelId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HotelResponseDto creatNewHotel(@NotBlank @Validated(Create.class) @RequestBody HotelNewDto newHotel) {

        log.info("\nHotel was created via hotels controller at time: " + LocalDateTime.now() + "\n");

        return hotelService.creatNewHotel(newHotel);
    }

    @PutMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    public HotelResponseDto updateHotelInfo(@Positive @PathVariable(name = "hotelId") Long hotelId,
                                            @NotBlank @Validated(Update.class)
                                            @RequestBody HotelNewDto hotelToUpdate) {

        log.info("\nHotel with id: %d was updated via hotels controller at time: ".formatted(hotelId)
                + LocalDateTime.now() + "\n");

        return hotelService.updateHotelInfo(hotelId, hotelToUpdate);
    }

    @DeleteMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HotelResponseDto removeHotelByHotelId(@Positive @PathVariable(name = "hotelId") Long hotelId) {

        log.info("\nHotel with id: %d was deleted via hotels controller at time: ".formatted(hotelId)
                + LocalDateTime.now() + "\n");

        return hotelService.removeHotelByHotellId(hotelId);
    }

    @PutMapping("/{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    public HotelResponseDto updateHotelRating(@PathVariable(name = "hotelId") Long hotelId,
                                              @NotBlank @Validated(Update.class)
                                              @RequestBody HotelNewDto hotelToRatingUpdate) {

        log.info("\nHotel with id: %d rating was updated via hotels controller at time: ".formatted(hotelId)
                + LocalDateTime.now() + "\n");
        return hotelService.updateHotelRating(hotelId, hotelToRatingUpdate);
    }
}
