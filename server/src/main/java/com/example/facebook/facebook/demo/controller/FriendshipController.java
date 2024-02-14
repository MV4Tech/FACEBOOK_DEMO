package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.FriendshipDto;
import com.example.facebook.facebook.demo.model.Friendship;
import com.example.facebook.facebook.demo.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friendship")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;


    // send friendship request
    @PostMapping("/request-friendship")
    public ResponseEntity<Void> sendFriendRequest( @RequestBody Friendship friendship){
        friendshipService.sendFriendRequest(friendship);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // accept friendship
    @PutMapping("/accept-friendship/{friendshipId}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable Long friendshipId){
        friendshipService.acceptFriendRequest(friendshipId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // deny friendship
    @PutMapping("/deny-friendship/{friendshipId}")
    public ResponseEntity<Void> denyFriendRequest(@PathVariable Long friendshipId){
        friendshipService.denyFriendRequest(friendshipId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // get all friendship requests
    @GetMapping("/all-friendship-requests/{userId}")
    public ResponseEntity<List<FriendshipDto>> getAllFriendshipsRequests(@PathVariable Long userId){
        return ResponseEntity.ok(friendshipService.getAllFriendshipRequests(userId));
    }

    @GetMapping("/all-friends/{userId}")
    public ResponseEntity<List<FriendshipDto>> getAllFriendsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(friendshipService.getAllFriendsByUserId(userId));
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
