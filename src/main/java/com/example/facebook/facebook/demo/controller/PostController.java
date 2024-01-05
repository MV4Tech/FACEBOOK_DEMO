package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/add-post/{id}")
    public ResponseEntity<Void> addPost(@RequestBody @Valid Post post, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
