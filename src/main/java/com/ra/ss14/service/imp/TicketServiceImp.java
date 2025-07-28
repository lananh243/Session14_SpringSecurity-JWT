package com.ra.ss14.service.imp;

import com.ra.ss14.model.dto.request.TicketBookingRequest;
import com.ra.ss14.model.entity.Showtime;
import com.ra.ss14.model.entity.Ticket;
import com.ra.ss14.model.entity.User;
import com.ra.ss14.repository.ShowTimeRepository;
import com.ra.ss14.repository.TicketRepository;
import com.ra.ss14.repository.UserRepository;
import com.ra.ss14.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketServiceImp implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Override
    public Ticket bookTicket(TicketBookingRequest request, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username not found"));
        Showtime showtime = showTimeRepository.findById(request.getShowtimeId()).orElseThrow(()-> new RuntimeException("Showtime not found"));
        Ticket ticket = Ticket.builder()
                .user(user)
                .showtime(showtime)
                .seatNumber(request.getSeatNumber())
                .bookingTime(LocalDateTime.now())
                .price(request.getPrice())
                .build();
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getMyTickets(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username not found"));
        return ticketRepository.findByUser(user);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
