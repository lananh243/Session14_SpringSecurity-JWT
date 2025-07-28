package com.ra.ss14.repository;

import com.ra.ss14.model.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowTimeRepository extends JpaRepository<Showtime, Long> {
}
