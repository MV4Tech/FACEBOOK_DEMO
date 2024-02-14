package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.ShareDto;
import com.example.facebook.facebook.demo.model.Notification;
import com.example.facebook.facebook.demo.model.Post;
import com.example.facebook.facebook.demo.model.Share;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.ShareRepository;
import com.example.facebook.facebook.demo.service.NotificationService;
import com.example.facebook.facebook.demo.service.PostService;
import com.example.facebook.facebook.demo.service.ShareService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService {

    private static final Logger logger = LoggerFactory.getLogger(ShareServiceImpl.class);

    private final ShareRepository shareRepository;
    private final PostService postService;
    private final UserService userService;
    private final NotificationService notificationService;

    @Override
    public void sharePost(Share share) {
        int shareCount = share.getShareCount()+1;
        Post post = postService.getPostById(share.getPost().getId()).get();
        share.setShareCount(shareCount);
        shareRepository.save(share);
        User sender = userService.getUserById(share.getSharer().getId());
        User receiver = userService.getUserById(post.getUser().getId());
        Notification notification = Notification.builder()
                .sender(sender)
                .receiver(receiver)
                .message(sender.getFirstName() + " " + sender.getLastName() + " shared your post " + post.getPostHead())
                .sentTime(share.getDateOfSharing())
                .build();

        notificationService.sendNotification(notification);
        logger.info("Post shared successfully");
    }

    // not sure if this is the correct implementation
    @Override
    public ShareDto displayShare(Long shareId) {
        return null;
    }

    @Override
    public Integer getShareCount(Long postId) {
       return shareRepository.countSharesByPostId(postId);

    }
}
