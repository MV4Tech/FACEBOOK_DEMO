package com.example.facebook.facebook.demo.mongodb.repository;

import com.example.facebook.facebook.demo.mongodb.enums.Status;
import com.example.facebook.facebook.demo.mongodb.model.UserMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessageRepository {
    List<UserMessage> findAllByStatus(Status status);
}
