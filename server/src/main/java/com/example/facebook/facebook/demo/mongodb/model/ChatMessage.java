package com.example.facebook.facebook.demo.mongodb.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String content;
    private LocalDateTime timestamp;

}
