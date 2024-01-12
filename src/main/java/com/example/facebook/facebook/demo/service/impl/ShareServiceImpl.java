package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.model.Share;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.ShareRepository;
import com.example.facebook.facebook.demo.service.PostService;
import com.example.facebook.facebook.demo.service.ShareService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;
    private final PostService postService;
    private final UserService userService;

    @Override
    public void sharePost(Share share) {
         Post post = postService.getPostById(share.getPost().getId()).get();
         User user = userService.getUserById(share.getSharer().getId());
         // todo add description
        // post.setDescription();
         post.setDateOfPosting(share.getDateOfSharing());
         post.setUser(user);
         // todo addPost method
         //postService.addPost(post);

    }
}
