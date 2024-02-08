package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.Page;

public interface PageService {
    void addPage(Page page);

    Page getPageById(Long id);

    void deletePage(Long id);
}
