package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.Notification;
import com.example.facebook.facebook.demo.repository.NotificationRepository;
import com.example.facebook.facebook.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    @Override
    public void sendNotification(Notification notification) {
        notificationRepository.save(notification);
        logger.info("Notification sent successfully from " + notification.getSender().getEmail() + " to " + notification.getUser().getEmail());

    }
}
