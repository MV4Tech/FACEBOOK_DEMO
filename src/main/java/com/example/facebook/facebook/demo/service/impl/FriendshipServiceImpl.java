package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.Friendship;
import com.example.facebook.facebook.demo.repository.FriendshipRepository;
import com.example.facebook.facebook.demo.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipRepository friendshipRepository;

    @Override
    public void sendFriendRequest(Friendship friendship) {

    }
}
