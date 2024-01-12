package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.PostPhotoDto;
import com.example.facebook.facebook.demo.model.PostPhoto;
import com.example.facebook.facebook.demo.service.PostPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post-photo")
@RequiredArgsConstructor
public class PostPhotoController    {

    private final PostPhotoService postPhotoService;

    // add photo
    // TODO - TEST
    @PostMapping("/add-photo")
    public ResponseEntity<Void> addPhoto(@RequestParam("image") MultipartFile file, @RequestBody PostPhoto postPhoto) throws IOException {
       postPhotoService.addPhoto(file,postPhoto);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // display photo
    // TODO - TEST
    @GetMapping("/display-photo/{postId}")
    public ResponseEntity<List<PostPhotoDto>> displayPhoto(@PathVariable Long postId){
        return ResponseEntity.ok(postPhotoService.displayPhoto(postId));
    }

    // delete photo
    // TODO - TEST
    @DeleteMapping("/delete-photo/{videoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long videoId){
        postPhotoService.deletePhoto(videoId);
        return ResponseEntity.noContent().build();
    }
}
