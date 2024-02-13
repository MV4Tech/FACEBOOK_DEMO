package com.example.facebook.facebook.demo.mongodb.service;

import com.example.facebook.facebook.demo.mongodb.model.UserMessage;

import java.util.List;

public interface UserMessageService {
    void saveUserMessage(UserMessage userMessage);
    void disconnectUser(UserMessage userMessage);
    List<UserMessage> findConnectedUsers();
}
