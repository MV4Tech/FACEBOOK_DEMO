package com.example.facebook.facebook.demo.mongodb.controller;

import com.example.facebook.facebook.demo.mongodb.model.UserMessage;
import com.example.facebook.facebook.demo.mongodb.service.UserMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class UserMessageController {
    /*
    private final UserMessageService userMessageService;


    @MessageMapping("/userMessage.addUserMessage")
    @SendTo("/user/topic")
    public UserMessage saveUserMessage(@Payload UserMessage userMessage) {
        userMessageService.saveUserMessage(userMessage);
        return userMessage;
    }

    @MessageMapping("/userMessage.disconnectUser")
    @SendTo("/user/topic")
    public UserMessage disconnectUser(@Payload UserMessage userMessage) {
        userMessageService.disconnectUser(userMessage);
        return userMessage;
    }

    @GetMapping("/online-users")
    public ResponseEntity<List<UserMessage>> findConnectedUsers() {
        return ResponseEntity.ok(userMessageService.findConnectedUsers());
    }

     */
}
