package com.example.facebook.facebook.demo.mongodb.service.impl;

import com.example.facebook.facebook.demo.mongodb.enums.Status;
import com.example.facebook.facebook.demo.mongodb.model.UserMessage;
import com.example.facebook.facebook.demo.mongodb.repository.UserMessageRepository;
import com.example.facebook.facebook.demo.mongodb.service.UserMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMessageServiceImpl implements UserMessageService {
/*
    private final UserMessageRepository userMessageRepository;

    public void saveUserMessage(UserMessage userMessage) {
        userMessage.setStatus(Status.ONLINE);
        userMessageRepository.save(userMessage);
    }

    public void disconnectUser(UserMessage userMessage) {
        var storedUserMessage = userMessageRepository.findById(userMessage.getId())
                .orElseThrow(null);
        if(storedUserMessage != null) {
            storedUserMessage.setStatus(Status.OFFLINE);
            userMessageRepository.save(userMessage);
        }

    }

    public List<UserMessage> findConnectedUsers() {

        return userMessageRepository.findAllByStatus(Status.ONLINE);
    }
    */

}
