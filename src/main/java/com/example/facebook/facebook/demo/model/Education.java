package com.example.facebook.facebook.demo.model;

import com.example.facebook.facebook.demo.enums.School;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private School typeOfSchool;


    @Column(nullable = false)
    private LocalDateTime startedDate;


    @Column
    private LocalDateTime graduationDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
