package com.ra.ss14.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id",referencedColumnName = "movie_id")
    private Movie movie;
    private LocalDateTime startTime;
    private String room;
}
