package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.FriendshipDto;
import com.example.facebook.facebook.demo.dto.NotificationDto;
import com.example.facebook.facebook.demo.enums.Status;
import com.example.facebook.facebook.demo.exception.FriendshipNotFoundException;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final NotificationService notificationService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(FriendshipServiceImpl.class);

    @Override
    public Friendship getFriendship(Long friendshipId) {
       Optional<Friendship> optionalFriendship = friendshipRepository.findById(friendshipId);
       if(optionalFriendship.isPresent()){
           //throw new RuntimeException("Friendship not found");
       }
       return optionalFriendship.get();
    }

    @Override
    public void sendFriendRequest(Friendship friendship) {
        User sender = userService.findById(friendship.getSender().getId()).get();
        User receiver = userService.findById(friendship.getReceiver().getId()).get();

        Notification notification = new Notification();
        notification.setMessage("You have a new friend request from " + sender.getFirstName() + " " + sender.getLastName());
        logger.info(receiver.getFirstName());
        notification.setReceiver(receiver);
        notification.setSender(sender);

        notificationService.sendNotification(notification);
        friendshipRepository.save(friendship);
        logger.info("Friend Request pending from " + sender.getFirstName() + " " + sender.getLastName() + " to " + receiver.getFirstName() + " " + receiver.getLastName());
    }

    @Override
    public void acceptFriendRequest(Long friendshipId) {
        Friendship friendship = getFriendship(friendshipId);
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

    @Override
    public void denyFriendRequest(Long friendshipId) {
        Friendship friendship = getFriendship(friendshipId);
        User sender = userService.findById(friendship.getSender().getId()).get();
        User receiver = userService.findById(friendship.getReceiver().getId()).get();

        Notification notification = new Notification();
        notification.setMessage(receiver.getFirstName() + " " + receiver.getLastName() + " has denied your friend request");
        notification.setReceiver(sender);
        notification.setSender(receiver);
        notificationService.sendNotification(notification);

        friendship.setStatus(Status.DENIED);
        friendship.setDateOfBecomingFriends(LocalDateTime.now());
        friendshipRepository.save(friendship);
        logger.info(sender.getFirstName() + " " + sender.getLastName() + " and " + receiver.getFirstName() + " " + receiver.getLastName() + " are not friends");
    }

    @Override
    public List<FriendshipDto> getAllFriendshipRequests(Long userId) {
        List<Friendship> friendships = friendshipRepository.findAllByReceiverIdAndStatus(userId, Status.PENDING);

        if(friendships.isEmpty()){
            throw new FriendshipNotFoundException("No friend requests found for user with id " + userId);
        }
        List<FriendshipDto> friendshipDtos = friendships.stream().map(friendship ->{
            FriendshipDto friendshipDto = new FriendshipDto();
            friendshipDto.setFriendshipID(friendship.getFriendshipID());
            friendshipDto.setSender(friendship.getSender().getFirstName() + " " + friendship.getSender().getLastName());
            friendshipDto.setReceiver(friendship.getReceiver().getFirstName() + " " + friendship.getReceiver().getLastName());
            friendshipDto.setDateOfBecomingFriends(friendship.getDateOfBecomingFriends());
            friendshipDto.setStatus(friendship.getStatus().toString());
            return friendshipDto;
        }).collect(Collectors.toList());

        return friendshipDtos;
    }

    @Override
    public List<FriendshipDto> getAllFriendsByUserId(Long userId) {
        List<Friendship> receivedFriendships = friendshipRepository.findAllByReceiverIdAndStatus(userId, Status.ACCEPTED);
        List<Friendship> sentFriendships = friendshipRepository.findAllBySenderIdAndStatus(userId, Status.ACCEPTED);

        if(receivedFriendships.isEmpty() && sentFriendships.isEmpty()){
            throw new FriendshipNotFoundException("No friends found for user with id " + userId);
        }

        List<FriendshipDto> friendshipDtos = Stream.concat(
                        receivedFriendships.stream(),
                        sentFriendships.stream())
                .map(friendship -> {
                    FriendshipDto friendshipDto = new FriendshipDto();
                    friendshipDto.setFriendshipID(friendship.getFriendshipID());
                    friendshipDto.setSender(friendship.getSender().getFirstName() + " " + friendship.getSender().getLastName());
                    friendshipDto.setReceiver(friendship.getReceiver().getFirstName() + " " + friendship.getReceiver().getLastName());
                    friendshipDto.setDateOfBecomingFriends(friendship.getDateOfBecomingFriends());
                    friendshipDto.setStatus(friendship.getStatus().toString());
                    return friendshipDto;
                })
                .collect(Collectors.toList());

        return friendshipDtos;
    }

    @Override
    public List<Friendship> findAllBySenderIdAndStatusOrReceiverIdAndStatus(Long senderId, Status status1, Long receiverId, Status status2) {
        return friendshipRepository.findAllBySenderIdAndStatusOrReceiverIdAndStatus(senderId, status1, receiverId, status2);
    }


}
