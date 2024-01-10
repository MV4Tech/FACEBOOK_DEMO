package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.PostPhoto;
import com.example.facebook.facebook.demo.service.PostPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/post-photo")
@RequiredArgsConstructor
public class PostPhotoController {

    private final PostPhotoService postPhotoService;

    // add photo
    // TODO - TEST
    @PostMapping("/add-photo")
    public ResponseEntity<Void> addPhoto(@RequestParam("image") MultipartFile file, @RequestBody PostPhoto postPhoto) throws IOException {
       postPhotoService.addPhoto(file,postPhoto);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
