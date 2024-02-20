package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.PostDto;
import com.example.facebook.facebook.demo.model.Post;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostService {
    Optional<Post> getPostById(Long postId);

    void addPost(Post post, Authentication authentication);

    Set<PostDto> getAllPostsByUserId(Authentication authentication);

    void deletePost(Long postId);

    void editPost(Post post, Long postId);

    void addPostPage(Post post, Authentication authentication);

    Set<Post> getAllPostsByPageId(Long pageId);
}
