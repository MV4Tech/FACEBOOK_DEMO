package com.example.facebook.facebook.demo.model;

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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private User sender;

    @Column
    private String postHead;

    @Column
    private LocalDateTime dateOfPosting;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private Set<Reaction> likes;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private Set<Share> shares;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private Set<PostPhoto> photos;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private Set<PostVideo> videos;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

}
