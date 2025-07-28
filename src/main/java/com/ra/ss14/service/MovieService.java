package com.ra.ss14.service;

import com.ra.ss14.model.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMovies();
    Movie getMovieById(Long id);
    Movie save(Movie movie);
    Movie update(Movie movie, Long id);
    void delete(Long id);
}
