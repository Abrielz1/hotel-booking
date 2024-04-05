package com.example.hotelbooking.user.model.dto.jwt;

import com.example.hotelbooking.user.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String username;

    private String email;

    private List<RoleType> roles;

    private String password;
}
