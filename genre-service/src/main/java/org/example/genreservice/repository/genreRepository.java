package org.example.genreservice.repository;

import org.example.genreservice.entity.genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface genreRepository extends JpaRepository<genres,String> {

}
