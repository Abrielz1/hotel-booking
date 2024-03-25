package com.example.hotelbooking.user.controller;

import com.example.hotelbooking.common.Update;
import com.example.hotelbooking.user.model.dto.user.UserNewDto;
import com.example.hotelbooking.user.model.dto.user.UserResponseDto;
import com.example.hotelbooking.user.service.UserService;
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
@RequestMapping("/hotel-booking/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> sendAllUserAccountsList(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                         @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("\nlist of users were sent from users controller" + " time: " + LocalDateTime.now() + "\n");
        PageRequest page = PageRequest.of(from / size, size);

        return userService.sendAllUserAccountsList(page);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto sendUsersAccountByUserId(@Positive @PathVariable(name = "userId") Long userId) {

        log.info(("\nUser with id: %d" +
                " was sent via users controller at time: ").formatted(userId)
                + LocalDateTime.now() + "\n");

        return userService.sendUsersAccountByUserId(userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto updateUsersAccountByUserId(@Positive @PathVariable(name = "userId") Long userId,
                                                      @Validated(Update.class)
                                                      @RequestBody UserNewDto updatedUserAccount) {

        log.info(("\nUser with id: %d" +
                " was updated via users controller at time: ").formatted(userId)
                + LocalDateTime.now() + "\n");

        return userService.updateUsersAccountByUserId(userId, updatedUserAccount);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserResponseDto deleteUsersAccountByUserId(@Positive @PathVariable(name = "userId") Long userId) {

        log.info(("\nUser with id: %d" +
                " was deleted via users controller at time: ").formatted(userId)
                + LocalDateTime.now() + "\n");

        return userService.deleteUsersAccountByUserId(userId);
    }
}
