package com.example.hotelbooking.user.service;

import com.example.hotelbooking.user.enums.RoleType;
import com.example.hotelbooking.user.model.dto.user.UserNewDto;
import com.example.hotelbooking.user.model.dto.user.UserResponseDto;
import org.springframework.data.domain.PageRequest;
import java.util.List;

public interface UserService {

    List<UserResponseDto> sendAllUserAccountsList(PageRequest page);

    UserResponseDto sendUsersAccountByUserId(Long userId);

    UserResponseDto registerUserAccount(UserNewDto newUserAccount, RoleType role);

    UserResponseDto updateUsersAccountByUserId(Long userId, UserNewDto updatedUserAccount);

    UserResponseDto deleteUsersAccountByUserId(Long userId);
}
