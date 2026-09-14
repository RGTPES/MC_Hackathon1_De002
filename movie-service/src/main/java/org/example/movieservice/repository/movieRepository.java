package org.example.movieservice.repository;

import org.example.movieservice.entity.movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface movieRepository extends JpaRepository<movie,String> {
}
