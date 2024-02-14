package com.example.facebook.facebook.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.facebook.facebook.demo.enums.Permission.*;

@RequiredArgsConstructor
public enum Role {
    /*
        * This enum is used to define the roles for the user and its permissions.
     */

    USER(
            Set.of(
                    USER_WRITE,
                    USER_DELETE,
                    USER_UPDATE
            )

    ),
    ADMIN(
            Set.of(
                    USER_READ,
                    USER_WRITE,
                    USER_DELETE,
                    USER_UPDATE
            )
    );

    /*
        * This const is used to get the permissions.
     */
    @Getter
private final Set<Permission> permissions;

    /*
        * This method is used to get the authorities.
     */
   public List<SimpleGrantedAuthority> getAuthorities(){
       var authorities = getPermissions()
               .stream()
               .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
               .collect(Collectors.toList());
       authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
   }

}
