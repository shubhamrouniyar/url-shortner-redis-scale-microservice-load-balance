package com.urlshortner.tinyurl;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class Caching {

    @Cacheable(value = "urls", key = "#shortUrl")
    public String getLongUrl(String shortUrl) {
        System.out.println("Fetching from DB for: " + shortUrl);
        // simulate DB lookup
        return "https://localhost://api/" + shortUrl;
    }

    @CachePut(value = "urls", key = "#shortUrl")
    public String saveMapping(String shortUrl, String longUrl) {
        System.out.println("Saving into DB: " + shortUrl + " -> " + longUrl);
        // simulate DB save
        return longUrl;
    }

    @CacheEvict(value = "urls", key = "#shortUrl")
    public void deleteMapping(String shortUrl) {
        System.out.println("Deleted " + shortUrl + " from DB and cache");
    }
}
