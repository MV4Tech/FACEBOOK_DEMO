package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.PostDto;
import com.example.facebook.facebook.demo.model.Post;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostService {
    Optional<Post> getPostById(Long postId);

    PostDto addPost(Post post, Authentication authentication);

    Set<PostDto> getAllPostsByUserId(Authentication authentication);

    PostDto deletePost(Long postId,Authentication authentication);

    PostDto editPost(Post post, Long postId, Authentication authentication);

    void addPostPage(Post post, Authentication authentication);

    Set<Post> getAllPostsByPageId(Long pageId);
}
