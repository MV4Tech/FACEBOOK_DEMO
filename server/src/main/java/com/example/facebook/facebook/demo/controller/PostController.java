package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.PostDto;
import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/add-post")
    public ResponseEntity<PostDto> addPost(@RequestBody @Valid Post post, Authentication authentication){
       PostDto postDto = postService.addPost(post,authentication);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/get-post-by-id/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId){
        return ResponseEntity.ok(postService.getPostById(postId).get());
    }

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<PostDto> deletePost(@PathVariable Long postId, Authentication authentication){
        PostDto postDto = postService.deletePost(postId,authentication);
        return ResponseEntity.ok(postDto);
    }

    @PutMapping("/edit-post/{postId}")
    public ResponseEntity<PostDto> editPost(@RequestBody Post post, @PathVariable Long postId,Authentication authentication){
       PostDto postDto = postService.editPost(post, postId, authentication);
        return ResponseEntity.ok(postDto);
    }


    @GetMapping("/get-all-post-by-user-id")
    public ResponseEntity<Set<PostDto>> getAllPostsByUserId(Authentication authentication){
        return ResponseEntity.ok(postService.getAllPostsByUserId(authentication));
    }


    // ---- page part ----
    // add new post page controller
    // TODO: TEST IT IN POSTMAN VE
    @PostMapping("/add-post-page")
    public ResponseEntity<Void> addPostPage(@RequestBody Post post, Authentication authentication){
        postService.addPostPage(post,authentication);
        return ResponseEntity.ok().build();
    }

    // TODO: get all posts by page id controller




}
