package org.example.genreservice.service;

import lombok.RequiredArgsConstructor;
import org.example.genreservice.entity.genres;
import org.example.genreservice.repository.genreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class genreService {
    private final genreRepository genreRepository;

    public Optional<genres> findById(String id) {

            return   genreRepository.findById(id);
    }

}
