package com.example.facebook.facebook.demo.mongodb.repository;

import com.example.facebook.facebook.demo.mongodb.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String>{

         Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);

}
