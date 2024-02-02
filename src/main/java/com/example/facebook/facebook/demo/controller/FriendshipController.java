package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Friendship;
import com.example.facebook.facebook.demo.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/accept-friendship/{friendshipId}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable Long friendshipId){
        friendshipService.acceptFriendRequest(friendshipId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // deny friendship
    // TODO: test this
    @PutMapping("/deny-friendship/{friendshipId}")
    public ResponseEntity<Void> denyFriendRequest(@PathVariable Long friendshipId){
       // friendshipService.denyFriendRequest(friendshipId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    // block friendship
    /*
    @PutMapping("/block-friendship/{friendshipId}")
    public ResponseEntity<Void> removeFriendship(@PathVariable Long friendshipId){
        friendshipService.removeFriendship(friendshipId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    */


}
