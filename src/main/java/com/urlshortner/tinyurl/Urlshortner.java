package com.urlshortner.tinyurl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.urlshortner.tinyurl.UtilityFuns;
import com.urlshortner.tinyurl.DatabaseInmemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api")
public class Urlshortner {
    private static final String BASE_URL = "https://tinyurl.com/";

    Url urlObject;
    @Autowired
    private AtomicCounter counter;

    @Async("threadPoolTaskExecutor")
    @GetMapping("{url}")
    public CompletableFuture<String> getMethodName(@PathVariable("url") String path) {
        String shortUrl = "";
        String hashcode = "";
        String URL = "http://localhost:8080/api/" + path;
        urlObject = new Url(URL, Instant.now(), Instant.now().plusSeconds(365L * 24 * 60 * 60));


        long count = counter.nextId();
        System.out.println("Generated ID: " + count);
        hashcode = UtilityFuns.hashing(count);
        System.out.println("Hashcode: " + hashcode);
        shortUrl = BASE_URL + hashcode;
        DatabaseInmemory database = new DatabaseInmemory();
        try {
            database.addEntry(shortUrl, URL);
        } catch (Exception e) {
            return CompletableFuture.completedFuture("Error: " + e.getMessage());
        }
        System.out.println(database.getAllURLList());
        return CompletableFuture.completedFuture(shortUrl);

        // future.get() // Blocking call is not required as it is handled by spring boot itself 
        // out async method returns a future and spring boot waits for it to complete and then sends the response back to client
        // so no need to block the thread here
    }
}
