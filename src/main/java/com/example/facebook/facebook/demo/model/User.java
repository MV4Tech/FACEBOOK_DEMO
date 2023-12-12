package com.example.facebook.facebook.demo.model;
import java.util.List;
import com.example.facebook.facebook.demo.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean isVerified = false;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Column
    private LocalDateTime dateOfCreation;

    @Column
    private char gender;

    @Column
    private LocalDateTime dateOfBirth;

    @Digits(integer = 10, fraction = 0)
    @Column
    private String phoneNumber;

    @Column
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Address> addresses;

    @OneToMany(mappedBy="user",fetch = FetchType.EAGER)
    private Set<Education> educations;

    @Lob
    @Column(name = "profile_picture", columnDefinition = "LONGBLOB")
    private byte[] profilePicture;

    @Lob
    @Column(name = "cover_picture", columnDefinition = "LONGBLOB")
    private byte[] coverPhoto;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Company> companies;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Post> feed;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserPageRelation> userPageRelations;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
     private Set<Photo> photos;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
     private Set<Video> videos;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Notification> notifications;

   // private Set<Message> messages;

   // private Set<Group> groups;






    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
