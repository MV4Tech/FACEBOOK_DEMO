package com.example.facebook.facebook.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipDto {
    private Long friendshipID;
    private String sender;
    private String receiver;
    private String status;
    private LocalDateTime dateOfBecomingFriends;
}
