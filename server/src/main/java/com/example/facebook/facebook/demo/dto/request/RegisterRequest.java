package com.example.facebook.facebook.demo.dto.request;

import com.example.facebook.facebook.demo.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class RegisterRequest {

    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String firstName;

    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    private String lastName;

    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    private String password;

    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    private String confirmPassword;

    private Role role;

}
