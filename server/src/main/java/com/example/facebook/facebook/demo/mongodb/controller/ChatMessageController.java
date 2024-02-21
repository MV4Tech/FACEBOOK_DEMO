package com.example.facebook.facebook.demo.mongodb.controller;

import com.example.facebook.facebook.demo.mongodb.model.ChatMessage;
import com.example.facebook.facebook.demo.mongodb.model.ChatNotification;
import com.example.facebook.facebook.demo.mongodb.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    /*
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(
        @Payload ChatMessage chatMessage
    ){
        ChatMessage saveMsg = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), "/queue/messages",
                ChatNotification.builder()
                        .chatId(saveMsg.getId())
                        .senderId(saveMsg.getSenderId())
                        .recipientId(saveMsg.getRecipientId())
                        .content(saveMsg.getContent())
                        .build()
        );
    }



    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId
    ){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }


     */
}
