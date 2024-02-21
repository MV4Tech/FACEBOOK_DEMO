package com.example.facebook.facebook.demo.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
