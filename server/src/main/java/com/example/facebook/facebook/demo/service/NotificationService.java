package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.NotificationDto;
import com.example.facebook.facebook.demo.model.Notification;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface NotificationService {
    void sendNotification(Notification notification, Authentication authentication);

    List<NotificationDto> getAllNotifications(Authentication authentication);
}
