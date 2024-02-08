package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.UserPageRelationDto;
import com.example.facebook.facebook.demo.exception.UserPageRelationNotFoundException;
import com.example.facebook.facebook.demo.model.Page;
import com.example.facebook.facebook.demo.model.UserPageRelation;
import com.example.facebook.facebook.demo.repository.UserPageRelationRepository;
import com.example.facebook.facebook.demo.service.UserPageRelationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPageRelationServiceImpl implements UserPageRelationService {

    private final UserPageRelationRepository userPageRelationRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserPageRelationServiceImpl.class);

    @Override
    public void addUserPageRelation(UserPageRelation userPageRelation) {
        userPageRelationRepository.save(userPageRelation);
        logger.info("User page relation added successfully" + userPageRelation.getUser().getId()
                + " liked,followed or subscribed to "
                + userPageRelation.getPage().getId() + " page");
    }

    @Override
    public UserPageRelation getUserPageRelationById(Long userPageRelationId) {
        Optional<UserPageRelation> optionalUserPageRelation
                = userPageRelationRepository.findById(userPageRelationId);
        if(!optionalUserPageRelation.isPresent()){
            throw new UserPageRelationNotFoundException("User page relation with id - " + userPageRelationId + " not found");
        }
        return optionalUserPageRelation.get();
    }

    @Override
    public void removeUserPageRelation(Long userPageRelationId) {
        userPageRelationRepository.delete(getUserPageRelationById(userPageRelationId));
    }

    @Override
    public List<UserPageRelationDto> getAllUserRelationsByUserId(Long userId) {

        List<UserPageRelation> userPageRelations = userPageRelationRepository.findAllByUserId(userId);
        if(userPageRelations.isEmpty()){
            throw new UserPageRelationNotFoundException("No user page relations found for user with id - " + userId);
        }
        List<UserPageRelationDto> userPageRelationDtos = userPageRelations.stream().map(userPageRelation -> {
            UserPageRelationDto userPageRelationDto = new UserPageRelationDto();
            userPageRelationDto.setId(userPageRelation.getId());
            userPageRelationDto.setUserName(userPageRelation.getUser().getFirstName());
            userPageRelationDto.setPageName(userPageRelation.getPage().getName());
            userPageRelationDto.setDateOfRelation(userPageRelation.getDateStartedFollowing());
            return userPageRelationDto;
        }).collect(Collectors.toList());
        logger.info("User page relations fetched successfully for user with id - " + userId);
        return userPageRelationDtos;
    }

    @Override
    public List<UserPageRelationDto> getAllPageRelationsByPageId(Long pageId) {
        List<UserPageRelation> userPageRelations = userPageRelationRepository.findAllByPageId(pageId);
        if(userPageRelations.isEmpty()){
            throw new UserPageRelationNotFoundException("No user page relations found for page with id - " + pageId);
        }
        List<UserPageRelationDto> userPageRelationDtos = userPageRelations.stream().map(userPageRelation -> {
            UserPageRelationDto userPageRelationDto = new UserPageRelationDto();
            userPageRelationDto.setId(userPageRelation.getId());
            userPageRelationDto.setUserName(userPageRelation.getUser().getFirstName());
            userPageRelationDto.setPageName(userPageRelation.getPage().getName());
            userPageRelationDto.setDateOfRelation(userPageRelation.getDateStartedFollowing());
            return userPageRelationDto;
        }).collect(Collectors.toList());
        logger.info("User page relations fetched successfully for page with id - " + pageId);
        return userPageRelationDtos;
    }

    @Override
    public Long getFollowersCount(Long pageId) {
        Long followersCount = userPageRelationRepository.countAllByPageId(pageId);
        logger.info("Followers count fetched successfully for page with id - " + pageId);
        return followersCount;
    }

    @Override
    public Long getLikedPageCount(Long userId) {
        Long likedPageCount = userPageRelationRepository.countAllByUserId(userId);
        logger.info("Liked page count fetched successfully for user with id - " + userId);
        return likedPageCount;
    }

    @Override
    public List<Page> getAllPagesByUserId(Long userId) {
        List<UserPageRelation> userPageRelations = userPageRelationRepository.findAllByUserId(userId);
        List<Page> pages = userPageRelations.stream().map(UserPageRelation::getPage).collect(Collectors.toList());
        logger.info("Pages fetched successfully for user with id - " + userId + " pages - ");
        return pages;
    }


}
