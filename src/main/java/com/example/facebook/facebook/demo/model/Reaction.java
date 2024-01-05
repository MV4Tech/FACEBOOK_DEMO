package com.example.facebook.facebook.demo.model;

import com.example.facebook.facebook.demo.enums.TypeReaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeReaction react;

    @Column
    private String username;

    @Column(name = "flag")
    private Boolean flag = false;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"likes"})
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
