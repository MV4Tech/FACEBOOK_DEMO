package com.example.facebook.facebook.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;

    @Column(name = "sent_time")
    private LocalDateTime sentTime;

    @ManyToOne
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
