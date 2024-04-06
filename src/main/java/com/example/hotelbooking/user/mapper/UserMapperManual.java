package com.example.hotelbooking.user.mapper;

import com.example.hotelbooking.user.enums.RoleType;
import com.example.hotelbooking.user.model.dto.user.UserNewDto;
import com.example.hotelbooking.user.model.dto.user.UserResponseDto;
import com.example.hotelbooking.user.model.entity.User;
import java.util.ArrayList;

public class UserMapperManual {

   public static User toUser(UserNewDto newUser) {

        return User.builder()
                .username(newUser.getUsername())
                .password(newUser.getPassword())
                .email(newUser.getEmail())
                .roles(newUser.getRoles())
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
