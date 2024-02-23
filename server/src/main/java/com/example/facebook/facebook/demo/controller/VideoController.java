package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Photo;
import com.example.facebook.facebook.demo.model.Video;
import com.example.facebook.facebook.demo.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/video")
public class VideoController {

    private final VideoService videoService;


    @PostMapping("/add-video")
    public ResponseEntity<?> addPhoto(@RequestParam("video") MultipartFile file, Authentication authentication, @RequestPart("data") Video video) throws IOException {
        String uploadMessage = videoService.addVideo(file,authentication,video);
        return ResponseEntity.status(HttpStatus.OK).body(uploadMessage);
    }
}
