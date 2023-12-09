package com.example.facebook.facebook.demo.model;

import com.example.facebook.facebook.demo.enums.TypeReaction;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String comment;

    @Column
    private User sender;

    @OneToMany(mappedBy = "comment")
    private Set<Reaction> reactions;

    @Column
    private LocalDateTime dateOfMessaging;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
