package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Notification;
import com.example.facebook.facebook.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send-notification")
    public ResponseEntity<Void> sendNotification(@RequestBody Notification notification){
       notificationService.sendNotification(notification);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // TODO: display notification
    // TODO: display all notifications

}
