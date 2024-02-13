package com.example.facebook.facebook.demo.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {
    private String chatId;
    private String senderId;
    private String recipientId;
    private String content;
}
