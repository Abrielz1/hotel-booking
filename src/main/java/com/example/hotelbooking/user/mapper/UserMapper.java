package com.example.hotelbooking.user.mapper;

import com.example.hotelbooking.user.model.dto.UserNewDto;
import com.example.hotelbooking.user.model.dto.UserResponseDto;
import com.example.hotelbooking.user.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User toUser(UserNewDto newUser);

    UserResponseDto toUserResponseDto(User user);
}
