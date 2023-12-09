package com.example.facebook.facebook.demo.model;

import com.example.facebook.facebook.demo.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FriendshipID")
    private Long friendshipID;

    @ManyToOne
    @JoinColumn(name = "UserID1", referencedColumnName = "id")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "UserID2", referencedColumnName = "id")
    private User user2;

    @Column(name = "Status", nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime dateOfBecomingFriends;

}
