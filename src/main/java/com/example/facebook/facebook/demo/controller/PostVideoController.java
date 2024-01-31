package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.PostPhotoDto;
import com.example.facebook.facebook.demo.dto.PostVideoDto;
import com.example.facebook.facebook.demo.model.PostPhoto;
import com.example.facebook.facebook.demo.model.PostVideo;
import com.example.facebook.facebook.demo.service.PostVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post-video")
@RequiredArgsConstructor
public class PostVideoController {

    private final PostVideoService postVideoService;

    // add video
    // max file size 4MB can be configured in application.yml
    @PostMapping("/add-video")
    public ResponseEntity<Void> addPhoto(@RequestParam("video") MultipartFile file, @RequestPart PostVideo postVideo) throws IOException {
        postVideoService.addVideo(file,postVideo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // display video
    @GetMapping("/display-video/{postId}")
    public ResponseEntity<List<PostVideoDto>> displayPhoto(@PathVariable Long postId){
        return ResponseEntity.ok(postVideoService.displayVideo(postId));
    }


    // delete video
    @DeleteMapping("/delete-video/{videoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long videoId){
        postVideoService.deleteVideo(videoId);
        return ResponseEntity.noContent().build();
    }
}
