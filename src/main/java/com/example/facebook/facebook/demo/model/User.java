package com.example.facebook.facebook.demo.model;
import java.util.List;
import com.example.facebook.facebook.demo.enums.Role;
import com.fasterxml.jackson.annotation.*;
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
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean isVerified = false;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    private LocalDateTime dateOfCreation;

    @Column
    private char gender;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    private LocalDateTime dateOfBirth;

    @Digits(integer = 10, fraction = 0)
    @Column
    private String phoneNumber;

    @Column
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("user")
    @EqualsAndHashCode.Exclude
    private Set<Address> addresses;

    @Column
    @OneToMany(mappedBy="user",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("user")
    @EqualsAndHashCode.Exclude
    private Set<Education> educations;

    @Lob
    @Column(name = "profile_picture", columnDefinition = "LONGBLOB")
    private byte[] profilePicture;

    @Lob
    @Column(name = "cover_picture", columnDefinition = "LONGBLOB")
    private byte[] coverPhoto;

    @Column
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("user")
    @EqualsAndHashCode.Exclude
    private Set<Company> companies;

    @Column
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
  //  @JsonManagedReference
    private Set<Post> feed;

    @Column
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  //  @JsonManagedReference
    private Set<UserPageRelation> userPageRelations;

    @Column
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  //  @JsonManagedReference
     private Set<Photo> photos;

    @Column
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
   // @JsonManagedReference
     private Set<Video> videos;

    @Column
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
   // @JsonManagedReference
    private Set<Notification> notifications;

   // private Set<Message> messages;

   // private Set<Group> groups;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
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
