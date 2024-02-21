package com.example.facebook.facebook.demo.mongodb.service.impl;


import com.example.facebook.facebook.demo.mongodb.model.ChatMessage;
import com.example.facebook.facebook.demo.mongodb.repository.ChatMessageRepository;
import com.example.facebook.facebook.demo.mongodb.service.ChatMessageService;
import com.example.facebook.facebook.demo.mongodb.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    /*
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true
        ).orElseThrow(); // create exception for chat room not found
        chatMessage.setChatId(chatId);
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }

     */

}
