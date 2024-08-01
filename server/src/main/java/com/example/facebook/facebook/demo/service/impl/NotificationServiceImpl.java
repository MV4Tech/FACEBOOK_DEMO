package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.NotificationDto;
import com.example.facebook.facebook.demo.exception.NotificationsNotFoundException;
import com.example.facebook.facebook.demo.model.Notification;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.NotificationRepository;
import com.example.facebook.facebook.demo.service.NotificationService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    @Override
    public void sendNotification(Notification notification, Authentication authentication) {
        logger.info(notification.getMessage());
        //long id = userService.findUserIdByAuthentication(authentication);
        //User receiver = userService.findById(notification.getReceiver().getId()).get();
       //User sender = userService.findById(id).get();
        notificationRepository.save(notification);
        //logger.info("Notification sent to " + receiver.getFirstName() + " " + receiver.getLastName() + " from " + sender.getFirstName() + " " + sender.getLastName() + " at " + notification.getSentTime());

    }

    @Override
    public List<NotificationDto> getAllNotifications(Authentication authentication) {
        long userId = userService.findUserIdByAuthentication(authentication);
        List<Notification> notifications = notificationRepository.findAllByReceiverId(userId);
        if(notifications.isEmpty()){
            throw new NotificationsNotFoundException("No notifications found for user with id " + userId);
        }

        List<NotificationDto> notificationDtos = notifications.stream().map(notification ->{
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setId(notification.getId());
            notificationDto.setMessage(notification.getMessage());
            notificationDto.setSentTime(notification.getSentTime());// aqui
            notificationDto.setReceiver(notification.getReceiver().getFirstName() + " " + notification.getReceiver().getLastName());
            notificationDto.setSender(notification.getSender().getFirstName() + " " + notification.getSender().getLastName());
            return notificationDto;
        }).collect(Collectors.toList());


        return notificationDtos;
    }
}
