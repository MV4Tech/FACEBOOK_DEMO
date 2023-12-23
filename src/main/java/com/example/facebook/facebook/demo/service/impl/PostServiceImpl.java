package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.repository.PostRepository;
import com.example.facebook.facebook.demo.service.PostService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostRepository postRepository;
    private final UserService userService;

    @Override
    public void addPost(Post post,Long id) {
        post.setUser(userService.getUserById(id));
        postRepository.save(post);
       logger.info("Post added successfully to user - " + userService.getUserById(id).getEmail());
    }

    @Override
    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

}
