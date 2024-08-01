package com.example.facebook.facebook.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    /*
        * This enum is used to define the permissions for the user.
    */

    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_DELETE("user:delete"),
    USER_UPDATE("user:update");

    /*
        * This const is used to get the permission.
     */
    @Getter
    private final String permission;
}
