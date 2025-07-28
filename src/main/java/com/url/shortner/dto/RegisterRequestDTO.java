package com.url.shortner.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Data
public class RegisterRequestDTO {

    @NotBlank(message = "username cannot be blank")
    private String username;

    @Email(message = "email is not correct")
    private String email;

    @NotBlank(message = "password cannot be empty")
    private String password;

    @NotEmpty(message = "role should not be empty")
    private Set<String> role;

}
