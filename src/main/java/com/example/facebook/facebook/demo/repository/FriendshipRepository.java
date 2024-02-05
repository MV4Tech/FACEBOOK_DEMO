package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.enums.Status;
import com.example.facebook.facebook.demo.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    List<Friendship> findAllByReceiverIdAndStatus(Long userId, Status status);

    List<Friendship> findAllBySenderIdAndStatus(Long userId, Status status);

    List<Friendship> findAllBySenderIdAndStatusOrReceiverIdAndStatus(Long senderId, Status status1, Long receiverId, Status status2);
}
