package com.ra.ss14.service.imp;

import com.ra.ss14.model.entity.Movie;
import com.ra.ss14.repository.MovieRepository;
import com.ra.ss14.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImp implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Movie movie, Long id) {
        Movie updatedMovie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        updatedMovie.setTitle(movie.getTitle());
        updatedMovie.setDescription(movie.getDescription());
        updatedMovie.setDuration(movie.getDuration());
        updatedMovie.setReleaseDate(movie.getReleaseDate());
        return movieRepository.save(updatedMovie);
    }

    @Override
    public void delete(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found");
        }
        movieRepository.deleteById(id);
    }
}
