package com.example.facebook.facebook.demo.mongodb.repository;
import com.example.facebook.facebook.demo.mongodb.model.ChatMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository{
    List<ChatMessage> findByChatId(String s);
}
