package com.ra.ss14.controller;

import com.ra.ss14.model.dto.response.APIResponse;
import com.ra.ss14.model.entity.Showtime;
import com.ra.ss14.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowtimeController {
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/api/showtimes")
    public ResponseEntity<APIResponse<List<Showtime>>> showtimes(){
        List<Showtime> showtimes = showtimeService.getShowtimes();
        return new ResponseEntity<>(new APIResponse<>(true, "Lấy danh sách thành công", showtimes, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping("/api/admin/showtimes")
    public ResponseEntity<APIResponse<Showtime>> addShowtime(@RequestBody Showtime showtime) {
        return new ResponseEntity<>(new APIResponse<>(true, "Thêm thành công!", showtimeService.save(showtime), HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
