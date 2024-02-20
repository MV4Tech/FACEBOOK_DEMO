package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.NotificationDto;
import com.example.facebook.facebook.demo.model.Notification;
import com.example.facebook.facebook.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    // Send a notification
    @PostMapping("/send-notification")
    public ResponseEntity<Void> sendNotification(@RequestBody Notification notification,Authentication authentication){
       notificationService.sendNotification(notification,authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Get all notifications for a user
    @GetMapping("/all-notifications")
    public ResponseEntity<List<NotificationDto>> getAllNotifications(Authentication authentication){
        return ResponseEntity.ok(notificationService.getAllNotifications(authentication));
    }


}
