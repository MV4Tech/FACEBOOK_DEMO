package com.example.facebook.facebook.demo.dto.request;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class RegisterRequest {

    @Email(message = "Invalid Email")
    private String email;

    private String password;
    private String confirmPassword;

}
