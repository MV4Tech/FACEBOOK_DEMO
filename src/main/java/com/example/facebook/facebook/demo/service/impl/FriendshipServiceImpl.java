package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.NotificationDto;
import com.example.facebook.facebook.demo.enums.Status;
import com.example.facebook.facebook.demo.model.Friendship;
import com.example.facebook.facebook.demo.model.Notification;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.FriendshipRepository;
import com.example.facebook.facebook.demo.service.FriendshipService;
import com.example.facebook.facebook.demo.service.NotificationService;

import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final NotificationService notificationService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(FriendshipServiceImpl.class);

    @Override
    public void sendFriendRequest(Friendship friendship) {
        User sender = userService.findById(friendship.getSender().getId()).get();
        User receiver = userService.findById(friendship.getReceiver().getId()).get();

        Notification notification = new Notification();
        notification.setMessage("You have a new friend request from " + sender.getFirstName() + " " + sender.getLastName());
        notification.setReceiver(receiver);
        notification.setSender(sender);

        notificationService.sendNotification(notification);
        friendshipRepository.save(friendship);
        logger.info("Friend Request pending from " + sender.getFirstName() + " " + sender.getLastName() + " to " + receiver.getFirstName() + " " + receiver.getLastName());
    }

    @Override
    public void acceptFriendRequest(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId).get();
        User sender = userService.findById(friendship.getSender().getId()).get();
        User receiver = userService.findById(friendship.getReceiver().getId()).get();

        Notification notification = new Notification();
        notification.setMessage(receiver.getFirstName() + " " + receiver.getLastName() + " has accepted your friend request");
        notification.setReceiver(sender);
        notification.setSender(receiver);
        notificationService.sendNotification(notification);

        friendship.setStatus(Status.ACCEPTED);
        friendship.setDateOfBecomingFriends(LocalDateTime.now());
        friendshipRepository.save(friendship);
        logger.info(sender.getFirstName() + " " + sender.getLastName() + " and " + receiver.getFirstName() + " " + receiver.getLastName() + " are now friends");
    }
}
