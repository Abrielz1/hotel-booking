package com.example.hotelbooking.user.mapper;

import com.example.hotelbooking.user.enums.RoleType;
import com.example.hotelbooking.user.model.dto.user.UserNewDto;
import com.example.hotelbooking.user.model.dto.user.UserResponseDto;
import com.example.hotelbooking.user.model.entity.User;
import java.util.ArrayList;
import java.util.Collections;

public class UserMapperManual {

   public static User toUser(UserNewDto newUser, RoleType role) {

        return User.builder()
                .username(newUser.getUsername())
                .password(newUser.getPassword())
                .email(newUser.getEmail())
                .roles(Collections.singletonList(role))
                .bookingList(new ArrayList<>())
                .build();
    }


    public static UserResponseDto toUserResponseDto(User user) {

        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
}
