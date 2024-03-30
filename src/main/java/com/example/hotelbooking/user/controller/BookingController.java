package com.example.hotelbooking.user.controller;

import com.example.hotelbooking.common.Create;
import com.example.hotelbooking.user.model.dto.booking.BookingNewDto;
import com.example.hotelbooking.user.model.dto.booking.BookingResponseDto;
import com.example.hotelbooking.user.service.BookingService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hotel-booking/{hotelId}/{roomId}")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/bookings")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingResponseDto> sendAllUserAccountsBookingList(
                                                                   @Positive @PathVariable(name = "hotelId") Long hotelId,
                                                                   @Positive @PathVariable(name = "roomId") Long roomId,
                                                                   @RequestParam LocalDate start,
                                                                   @RequestParam LocalDate end,
                                                                   @PositiveOrZero @RequestParam(defaultValue = "0")
                                                                   Integer from,
                                                                   @Positive @RequestParam(defaultValue = "10")
                                                                   Integer size) {

        log.info("\nList of bookings were sent from bookings controller"
                + " at time: " + LocalDateTime.now() + "\n");
        PageRequest page = PageRequest.of(from / size, size);

        return bookingService.sendAllUserAccountsBookingList(hotelId, roomId, start, end, page);
    }

    @PostMapping("/check-in")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponseDto createCheckIn(@Positive @PathVariable(name = "hotelId") Long hotelId,
                                            @Positive @PathVariable(name = "roomId") Long roomId,
                                            @Positive @RequestParam Long userId,
                                            @NotNull @Validated(Create.class)
                                            @RequestBody BookingNewDto newCheckIn) {

        log.info("\nCheck in user were create from bookings controller"
                + " at time: " + LocalDateTime.now() + "\n");

        return bookingService.createCheckIn(hotelId, roomId, userId, newCheckIn);
    }
 }
