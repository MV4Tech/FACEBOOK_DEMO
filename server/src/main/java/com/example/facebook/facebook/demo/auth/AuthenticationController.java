package com.example.facebook.facebook.demo.auth;

import com.example.facebook.facebook.demo.dto.request.AuthenticationRequest;
import com.example.facebook.facebook.demo.dto.request.RegisterRequest;
import com.example.facebook.facebook.demo.dto.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

   private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/refresh-token")
    @CrossOrigin(origins = "http://localhost:5173")
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }

    @GetMapping(path = "/confirm")
    public ModelAndView confirm(@RequestParam("token") String token){

        if(authenticationService.confirmToken(token)){
            // Redirect to the login page
            return new ModelAndView(new RedirectView("http://localhost:5173/login"));
        }else{
            // Handle confirmation failure
            return new ModelAndView("confirmation-failure-page"); // Example: return a confirmation failure page
        }
    }





}
