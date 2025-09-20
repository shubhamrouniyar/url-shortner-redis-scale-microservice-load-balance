package com.urlshortner.tinyurl;

import java.time.Instant;

public class Url {
    private String lonUrl;
    private Instant createdAt;
    private Instant expiryAt;

    public Url(String lonUrl, Instant createdAt, Instant expiryAt) {
        this.lonUrl = lonUrl;
        this.createdAt = createdAt;
        this.expiryAt = expiryAt;
    }

    public String getLonUrl() {
        return lonUrl;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public Instant getExpiryAt() {
        return expiryAt;
    }
    public void setLonUrl(String lonUrl) {
        this.lonUrl = lonUrl;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public void setExpiryAt(Instant expiryAt) {
        this.expiryAt = expiryAt;
    }
}
