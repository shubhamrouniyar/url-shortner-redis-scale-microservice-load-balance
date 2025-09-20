package com.urlshortner.tinyurl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterConfig {
    
    @Bean
    public AtomicCounter idGenerator() {
        return new AtomicCounter(1,1); // assign unique nodeId per replica
    }
}
