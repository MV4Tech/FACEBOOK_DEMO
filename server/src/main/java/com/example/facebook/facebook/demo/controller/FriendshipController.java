package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.FriendshipDto;
import com.example.facebook.facebook.demo.model.Friendship;
import com.example.facebook.facebook.demo.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friendship")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;


    // send friendship request
    @PostMapping("/request-friendship")
    public ResponseEntity<Void> sendFriendRequest(@RequestBody Friendship friendship, Authentication authentication){
        friendshipService.sendFriendRequest(friendship, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // accept friendship
    @PutMapping("/accept-friendship/{friendshipId}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable Long friendshipId, Authentication authentication){
        friendshipService.acceptFriendRequest(friendshipId,authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // deny friendship
    @PutMapping("/deny-friendship/{friendshipId}")
    public ResponseEntity<Void> denyFriendRequest(@PathVariable Long friendshipId, Authentication authentication){
        friendshipService.denyFriendRequest(friendshipId, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // get all friendship requests
    @GetMapping("/all-friendship-requests")
    public ResponseEntity<List<FriendshipDto>> getAllFriendshipsRequests(Authentication authentication){
        return ResponseEntity.ok(friendshipService.getAllFriendshipRequests(authentication));
    }

    // get all friends
    @GetMapping("/all-friends")
    public ResponseEntity<List<FriendshipDto>> getAllFriendsByUserId(Authentication authentication){
        return ResponseEntity.ok(friendshipService.getAllFriendsByUserId(authentication));
    }


    // block friendship
    // is yet to be implemented in near future
    /*
    @PutMapping("/block-friendship/{friendshipId}")
    public ResponseEntity<Void> removeFriendship(@PathVariable Long friendshipId){
        friendshipService.removeFriendship(friendshipId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    */


}
