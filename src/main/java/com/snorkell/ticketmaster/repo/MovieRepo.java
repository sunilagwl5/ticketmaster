package com.snorkell.ticketmaster.repo;

import com.snorkell.ticketmaster.entity.MovieD;
import com.snorkell.ticketmaster.model.MovieShows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepo extends JpaRepository<MovieD, Integer> {

    @Query(name = "MovieD.getMovieShows", nativeQuery = true)
    List<MovieShows> getMovieShows(@Param("movieId") int movieId);
}
