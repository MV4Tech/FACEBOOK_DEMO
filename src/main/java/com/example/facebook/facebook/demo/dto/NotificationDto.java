package com.example.facebook.facebook.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto {
    private Long id;
    private String message;
    private LocalDateTime sentTime;
    private String sender;
    private String receiver;
}
