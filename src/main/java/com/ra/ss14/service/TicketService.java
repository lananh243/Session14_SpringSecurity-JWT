package com.ra.ss14.service;

import com.ra.ss14.model.dto.request.TicketBookingRequest;
import com.ra.ss14.model.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(TicketBookingRequest request, String username);
    List<Ticket> getMyTickets(String username);
    List<Ticket> getAllTickets();
}
