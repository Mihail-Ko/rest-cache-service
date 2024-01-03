package com.example.restservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheManageService {

    private final String cacheName = "books";
    private final String cacheNamePage = "pages";

    @Caching(evict = {
        @CacheEvict(value = cacheName, allEntries = true),
        @CacheEvict(cacheNames = cacheNamePage, allEntries = true)})
    public void clearCache() {
        log.info("Invalidate books and pages cache");
    }
}