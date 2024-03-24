package com.example.hotelbooking.user.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserNewDto {

    @Size(max=32)
    @NotBlank
    private String username;

    @Size(max=32)
    @Email
    @NotBlank
    private String email;

    @Size(max=32)
    @NotBlank
    private String password;
}
