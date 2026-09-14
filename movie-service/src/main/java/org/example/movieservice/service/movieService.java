package org.example.movieservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.example.movieservice.client.GenreClient;
import org.example.movieservice.entity.movie;
import org.example.movieservice.repository.movieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class movieService {
    private final movieRepository movieRepository;
    private final GenreClient genreClient;
    
    public List<movie> findAll() {
        return movieRepository.findAll();
    }
    
    public movie addMovie(movie movie) {
        // Kiểm tra xem genreId có tồn tại không qua Feign Client
        try {
            ResponseEntity<Object> response = genreClient.findById(movie.getGenreId());
            
            // Nếu genre không tồn tại (404)
            if (response.getStatusCode().value() == 404 || response.getBody() == null) {
                throw new GenreNotFoundException("Genre with id " + movie.getGenreId() + " not found");
            }
            
            // Nếu genre tồn tại, lưu phim vào database
            return movieRepository.save(movie);
            
        } catch (FeignException.NotFound e) {
            // Feign trả 404
            throw new GenreNotFoundException("Genre with id " + movie.getGenreId() + " not found");
        } catch (FeignException e) {
            // Lỗi kết nối hoặc lỗi service khác
            throw new ServiceCommunicationException("Cannot connect to genre-service: " + e.getMessage());
        }
    }
    
    // Custom exceptions
    public static class GenreNotFoundException extends RuntimeException {
        public GenreNotFoundException(String message) {
            super(message);
        }
    }
    
    public static class ServiceCommunicationException extends RuntimeException {
        public ServiceCommunicationException(String message) {
            super(message);
        }
    }
}
