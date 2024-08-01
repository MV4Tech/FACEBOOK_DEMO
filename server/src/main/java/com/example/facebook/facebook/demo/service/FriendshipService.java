package com.example.facebook.facebook.demo.service;


import com.example.facebook.facebook.demo.dto.FriendshipDto;
import com.example.facebook.facebook.demo.enums.Status;
import com.example.facebook.facebook.demo.model.Friendship;
import com.example.facebook.facebook.demo.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface FriendshipService {

    Friendship getFriendship(Long friendshipId);
    void sendFriendRequest(Friendship friendship, Authentication authentication);

    void acceptFriendRequest(Long friendshipId,Authentication authentication);

    void denyFriendRequest(Long friendshipId,Authentication authentication);

    List<FriendshipDto> getAllFriendshipRequests(Authentication authentication);

    List<FriendshipDto> getAllFriendsByUserId(Authentication authentication);

    List<Friendship> findAllBySenderIdAndStatusOrReceiverIdAndStatus(Long senderId, Status status1, Long receiverId, Status status2);


}
