package com.urlshortner.tinyurl;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseInmemory {
    public ConcurrentHashMap<String, String> database;

    public DatabaseInmemory() {
        this.database = new ConcurrentHashMap<>();
    }

    public void addEntry(String shortUrl, String longUrl) {
        if(database.containsKey(shortUrl)) {
            throw new RuntimeException("Short URL already exists");
        }
        database.put(shortUrl, longUrl);
    }
    public void removeEntry(String shortUrl, String longUrl) {
        if(database.containsKey(shortUrl)) {
            database.remove(shortUrl);
        } else {
            throw new RuntimeException("Short URL does not exist");
        }
    }

    public ConcurrentHashMap<String, String> getAllURLList() {
        return database; 
    }
}
