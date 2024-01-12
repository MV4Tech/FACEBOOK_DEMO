package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Share;
import com.example.facebook.facebook.demo.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/share")
@RequiredArgsConstructor
public class ShareController {
    private final ShareService shareService;

    // share post
    @PostMapping("/share-post")
    public ResponseEntity<Void> sharePost(@RequestBody Share share){
        shareService.sharePost(share);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
