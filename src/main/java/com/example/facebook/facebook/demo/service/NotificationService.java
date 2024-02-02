package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.NotificationDto;
import com.example.facebook.facebook.demo.model.Notification;

import java.util.List;

public interface NotificationService {
    void sendNotification(Notification notification);

    List<NotificationDto> getAllNotifications(Long userId);
}
