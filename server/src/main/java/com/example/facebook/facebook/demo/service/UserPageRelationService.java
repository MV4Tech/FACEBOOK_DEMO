package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.UserPageRelationDto;
import com.example.facebook.facebook.demo.model.Page;
import com.example.facebook.facebook.demo.model.UserPageRelation;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserPageRelationService {
    void addUserPageRelation(UserPageRelation userPageRelation, Authentication authentication);

    UserPageRelation getUserPageRelationById(Long userPageRelationId);

    void removeUserPageRelation(Long userPageRelationId);

    List<UserPageRelationDto> getAllUserRelationsByUserId(Authentication authentication);

    List<UserPageRelationDto> getAllPageRelationsByPageId(Long pageId);

    Long getFollowersCount(Long pageId);

    Long getLikedPageCount(Authentication authentication);

    List<Page> getAllPagesByUserId(Long userId);
}
