package com.ra.ss14.service;

import com.ra.ss14.model.entity.Showtime;

import java.util.List;

public interface ShowtimeService {
    List<Showtime> getShowtimes();
    Showtime save(Showtime showtime);
}
