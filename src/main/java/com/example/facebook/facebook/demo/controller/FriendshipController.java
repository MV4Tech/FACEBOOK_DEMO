package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Friendship;
import com.example.facebook.facebook.demo.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/friendship")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;
/*
    @PostMapping("/add-friendship")
    public ResponseEntity<Void> addFriendship(@RequestBody Friendship friendship){
        friendshipService.addFriendship(friendship);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

 */

    @PostMapping("/request-friendship")
    public ResponseEntity<Void> sendFriendRequest( @RequestBody Friendship friendship){
        friendshipService.sendFriendRequest(friendship);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
