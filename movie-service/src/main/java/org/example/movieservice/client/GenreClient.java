package org.example.movieservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "genre-service")
public interface GenreClient {
    
    @GetMapping("/api/genres/{id}")
    ResponseEntity<Object> findById(@PathVariable String id);
}
