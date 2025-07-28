package com.ra.ss14.service.imp;

import com.ra.ss14.model.entity.Showtime;
import com.ra.ss14.repository.ShowTimeRepository;
import com.ra.ss14.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeServiceImp implements ShowtimeService {
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Override
    public List<Showtime> getShowtimes() {
        return showTimeRepository.findAll();
    }

    @Override
    public Showtime save(Showtime showtime) {
        return showTimeRepository.save(showtime);
    }
}
