package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.ShareDto;
import com.example.facebook.facebook.demo.model.Share;
import org.springframework.security.core.Authentication;

public interface ShareService {

    void sharePost(Share share, Authentication authentication);

    ShareDto displayShare(Long shareId);

    Integer getShareCount(Long postId);
}
