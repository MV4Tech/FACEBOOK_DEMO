package com.example.facebook.facebook.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "text")
    private String about;

    @Lob
    @Column(name = "profile_picture", columnDefinition = "LONGBLOB")
    private byte[] profilePicture;

    @Lob
    @Column(name = "cover_picture", columnDefinition = "LONGBLOB")
    private byte[] coverPhoto;

    @Digits(integer = 10, fraction = 0)
    @Column
    private String phoneNumber;

    @Column
    private LocalDateTime dateOfCreation;

    @OneToMany(mappedBy = "page",fetch = FetchType.EAGER)
    private Set<Address> addresses;

    @OneToMany(mappedBy = "page",fetch = FetchType.EAGER)
    private Set<Post> posts;

    @Column
    private Set<LocalDateTime> businessHours;

    @OneToMany(mappedBy = "page",fetch = FetchType.EAGER)
    private Set<Photo> photos;

    @OneToMany(mappedBy = "page",fetch = FetchType.EAGER)
    private Set<Video> videos;

    @OneToMany(mappedBy = "page", fetch = FetchType.EAGER)
    private Set<UserPageRelation> userPageRelations;

   // private Set<Event> events;


}
