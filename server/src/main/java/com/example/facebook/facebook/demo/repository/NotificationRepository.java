package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByReceiverId(Long userId);
}
