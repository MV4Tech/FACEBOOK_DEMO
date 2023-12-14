package com.example.facebook.facebook.demo.auth;

import com.example.facebook.facebook.demo.dto.request.AuthenticationRequest;
import com.example.facebook.facebook.demo.dto.request.RegisterRequest;
import com.example.facebook.facebook.demo.dto.response.AuthenticationResponse;
import com.example.facebook.facebook.demo.enums.Role;
import com.example.facebook.facebook.demo.exception.InvalidCredentialsException;
import com.example.facebook.facebook.demo.repository.UserRepository;
import com.example.facebook.facebook.demo.service.JwtService;
import com.example.facebook.facebook.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import com.example.facebook.facebook.demo.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService  {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // register user service
    public AuthenticationResponse register(RegisterRequest request) {

        // check if passwords match
        if(!(request.getPassword().equals(request.getConfirmPassword()))){
            throw new InvalidCredentialsException("Passwords do not match!");
        }


        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .dateOfCreation(LocalDateTime.now())
                .isVerified(false)
                .role(Role.USER)
                .build();

        var savedUser = userService.saveUser(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    // authenticate user service
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = userService.findByEmail(request.getEmail()).get();
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
                return AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .build();


        }catch(InvalidCredentialsException e){
            throw new InvalidCredentialsException("Invalid email or password!");
        }


    }

    // refresh token service
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {

        final String authHeader = request.getHeader("Authorization");
        final String refreshToken;
        final String username;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);

        if(username != null){

            var user = userService.findByEmail(username).get();
            if(jwtService.isTokenValid(refreshToken,user)){
                var accessToken = jwtService.generateToken(user);
                var newRefreshToken = jwtService.generateRefreshToken(user);
                var authenticationResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(newRefreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(),authenticationResponse);
            }
        }
    }

}
