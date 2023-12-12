package com.example.facebook.facebook.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Share{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private User sharer;

    @Column
    private LocalDateTime dateOfSharing;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
