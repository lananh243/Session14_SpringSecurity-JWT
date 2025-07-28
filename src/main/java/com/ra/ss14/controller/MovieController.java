package com.ra.ss14.controller;

import com.ra.ss14.model.dto.response.APIResponse;
import com.ra.ss14.model.entity.Movie;
import com.ra.ss14.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/api/movies")
    public ResponseEntity<APIResponse<List<Movie>>> getAllMovies(){
        List<Movie> movies = movieService.getMovies();
        return new ResponseEntity<>(new APIResponse<>(true, "Lấy danh sách film thành công!", movies, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping("/api/admin/movies")
    public ResponseEntity<APIResponse<Movie>> addMovie(@RequestBody Movie movie){
        return new ResponseEntity<>(new APIResponse<>(true, "Thêm film thành công!", movieService.save(movie), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/api/admin/movies/{id}")
    public ResponseEntity<APIResponse<Movie>> updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        return new ResponseEntity<>(new APIResponse<>(true, "Cập nhật thành công!", movieService.update(movie, id), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/movies/{id}")
    public ResponseEntity<APIResponse<Movie>> deleteMovie(@PathVariable Long id){
        Movie movie = movieService.getMovieById(id);
        movieService.delete(id);
        return new ResponseEntity<>(new APIResponse<>(true, "Xóa thành công!", movie, HttpStatus.OK), HttpStatus.OK);
    }
}
