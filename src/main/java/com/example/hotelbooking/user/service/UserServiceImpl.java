package com.example.hotelbooking.user.service;

import com.example.hotelbooking.exception.exceptions.ObjectNotFoundException;
import com.example.hotelbooking.user.enums.RoleType;
import com.example.hotelbooking.user.model.dto.UserNewDto;
import com.example.hotelbooking.user.model.dto.UserResponseDto;
import com.example.hotelbooking.user.model.entity.Role;
import com.example.hotelbooking.user.model.entity.User;
import com.example.hotelbooking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import static com.example.hotelbooking.user.mapper.UserMapper.USER_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> sendAllUserAccountsList(PageRequest page) {

        log.info("\nAll users accounts list were sent via users service at time: " + LocalDateTime.now() + "\n");
        return userRepository.findAll()
                .stream()
                .map(USER_MAPPER::toUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto sendUsersAccountByUserId(Long userId) {

        log.info("\nUser account was sent with id: %d via users service at time: ".formatted(userId)
                + LocalDateTime.now() + "\n");
        return USER_MAPPER.toUserResponseDto(checkUserInDb(userId));
    }

    @Override
    public UserResponseDto registerUserAccount(UserNewDto newUserAccount, RoleType role) {

        User userToSave = USER_MAPPER.toUser(newUserAccount);
        Role userRole = new Role();
        Role.from(role);
        userToSave.setRole(List.of(userRole));

        log.info("\nUser account was created via users service at time: "
                + LocalDateTime.now() + "\n");
        return USER_MAPPER.toUserResponseDto(userToSave);
    }

    @Override
    public UserResponseDto updateUsersAccountByUserId(Long userId, UserNewDto updatedUserAccount) {

        User userFromDBToUpdate = checkUserInDb(userId);

        if (updatedUserAccount == null) {
            log.warn("No room fields for update");
            throw new ObjectNotFoundException("No user fields for update");
        }

        if (StringUtils.hasText(updatedUserAccount.getUsername())) {
            userFromDBToUpdate.setUsername(updatedUserAccount.getUsername());
        }

        if (StringUtils.hasText(updatedUserAccount.getPassword())) {
            userFromDBToUpdate.setPassword(updatedUserAccount.getPassword());
        }

        if (StringUtils.hasText(updatedUserAccount.getEmail())) {
            userFromDBToUpdate.setEmail(updatedUserAccount.getEmail());
        }

        log.info(("\nUser account with userId: %d" +
                " was updated via users service at time: ").formatted(userId) +
                LocalDateTime.now() + "\n");

        return USER_MAPPER.toUserResponseDto(userRepository.save(userFromDBToUpdate));
    }

    @Override
    public UserResponseDto deleteUsersAccountByUserId(Long userId) {

        User userToDeleteById = checkUserInDb(userId);
        userRepository.findById(userId).ifPresent(userRepository::delete);

        log.info("\nUser account with id: %d was deleted via users service at time: ".formatted(userId)
                + LocalDateTime.now() + "\n");

        return USER_MAPPER.toUserResponseDto(userToDeleteById);
    }

    private User checkUserInDb(Long userID) {
        log.warn("No Hotel for update");
        return userRepository.findById(userID).orElseThrow(() ->
                new ObjectNotFoundException("User was not present"));
    }
}
