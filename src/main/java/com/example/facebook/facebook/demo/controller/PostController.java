package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.PostDto;
import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/add-post")
    public ResponseEntity<Void> addPost(@RequestBody @Valid Post post){
        postService.addPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-post-by-id/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getPostById(postId).get());
    }

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/edit-post/{postId}")
    public ResponseEntity<Void> editPost(@RequestBody Post post, @PathVariable Long postId){
        postService.editPost(post, postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/get-all-post-by-user-id/{userId}")
    public ResponseEntity<Set<PostDto>> getAllPostsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(postService.getAllPostsByUserId(userId));
    }






}
