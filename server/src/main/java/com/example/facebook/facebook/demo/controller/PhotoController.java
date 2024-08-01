package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Photo;
import com.example.facebook.facebook.demo.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping("/add-photo")
    public ResponseEntity<?> addPhoto(@RequestParam("image") MultipartFile file, Authentication authentication, @RequestPart("data") Photo photo) throws IOException {
        String uploadMessage = photoService.addPhoto(file,authentication,photo);
        return ResponseEntity.status(HttpStatus.OK).body(uploadMessage);
    }

    /*
    @GetMapping("/display-photo")
    public ResponseEntity<byte[]> displayProfileImage(Authentication authentication) throws IOException{
        byte[] imageBytes = userService.displayProfileImage(authentication);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    */

}
