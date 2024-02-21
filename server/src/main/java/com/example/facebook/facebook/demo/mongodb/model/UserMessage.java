package com.example.facebook.facebook.demo.mongodb.model;

import com.example.facebook.facebook.demo.mongodb.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessage {
    private String id;
    private String nickName;
    private String fullName;
    private Status status;
}
