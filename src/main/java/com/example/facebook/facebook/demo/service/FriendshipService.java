package com.example.facebook.facebook.demo.service;


import com.example.facebook.facebook.demo.dto.FriendshipDto;
import com.example.facebook.facebook.demo.model.Friendship;

import java.util.List;

public interface FriendshipService {

    Friendship getFriendship(Long friendshipId);
    void sendFriendRequest(Friendship friendship);

    void acceptFriendRequest(Long friendshipId);

    void denyFriendRequest(Long friendshipId);

    List<FriendshipDto> getAllFriendshipRequests(Long userId);

    List<FriendshipDto> getAllFriendsByUserId(Long userId);
}
