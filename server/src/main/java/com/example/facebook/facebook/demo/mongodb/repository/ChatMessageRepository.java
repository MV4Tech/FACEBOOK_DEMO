package com.example.facebook.facebook.demo.mongodb.repository;

import com.example.facebook.facebook.demo.mongodb.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByChatId(String s);
}
