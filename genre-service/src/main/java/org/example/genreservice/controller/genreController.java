package org.example.genreservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.genreservice.entity.genres;
import org.example.genreservice.service.genreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/genres")
public class genreController {
    private final genreService genreService;
    
    @GetMapping("{id}")
    public ResponseEntity<genres> findById(@PathVariable String id) {
        return genreService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
