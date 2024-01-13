package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.repository.PageRepository;
import com.example.facebook.facebook.demo.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PageRepository pageRepository;
}
