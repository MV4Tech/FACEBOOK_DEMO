package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.ShareDto;
import com.example.facebook.facebook.demo.model.Share;

public interface ShareService {

    void sharePost(Share share);

    ShareDto displayShare(Long shareId);

    Integer getShareCount(Long postId);
}
