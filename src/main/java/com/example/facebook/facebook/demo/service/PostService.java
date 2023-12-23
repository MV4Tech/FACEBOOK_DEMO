package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.Post;

import java.util.Optional;

public interface PostService {
    void addPost(Post post, Long id);

    Optional<Post> getPostById(Long postId);
}
