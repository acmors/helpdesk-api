package com.helpdesk_api.web.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLogin (
        @NotBlank
        @Email(message = "Invalid Email format.", regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
        String email,

        @NotBlank
        @Size(min = 6)
        String password
){
}
