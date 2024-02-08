package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.UserPageRelationDto;
import com.example.facebook.facebook.demo.model.Page;
import com.example.facebook.facebook.demo.model.UserPageRelation;

import java.util.List;

public interface UserPageRelationService {
    void addUserPageRelation(UserPageRelation userPageRelation);

    UserPageRelation getUserPageRelationById(Long userPageRelationId);

    void removeUserPageRelation(Long userPageRelationId);

    List<UserPageRelationDto> getAllUserRelationsByUserId(Long userId);

    List<UserPageRelationDto> getAllPageRelationsByPageId(Long pageId);

    Long getFollowersCount(Long pageId);

    Long getLikedPageCount(Long userId);

    List<Page> getAllPagesByUserId(Long userId);
}
