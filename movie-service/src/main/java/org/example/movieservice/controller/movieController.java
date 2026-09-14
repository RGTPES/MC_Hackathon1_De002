package org.example.movieservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.movieservice.entity.movie;
import org.example.movieservice.service.movieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/movies")
public class movieController {
    private final movieService movieService;
    
    @GetMapping
    public List<movie> findAll() {
        return movieService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<?> addMovie(@RequestBody movie movie) {
        try {
            movie savedMovie = movieService.addMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
            
        } catch (movieService.GenreNotFoundException e) {
            // Genre không tồn tại - trả 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
                    
        } catch (movieService.ServiceCommunicationException e) {
            // Lỗi kết nối service - trả 503
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(e.getMessage());
                    
        } catch (Exception e) {
            // Lỗi database hoặc lỗi khác - trả 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving movie: " + e.getMessage());
        }
    }
}
