package com.ra.ss14.controller;

import com.ra.ss14.model.dto.request.TicketBookingRequest;
import com.ra.ss14.model.dto.response.APIResponse;
import com.ra.ss14.model.entity.Ticket;
import com.ra.ss14.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/api/tickets/book")
    public ResponseEntity<APIResponse<Ticket>> bookTicket(@RequestBody TicketBookingRequest request, String username) {
        Ticket ticket = ticketService.bookTicket(request, username);
        return new ResponseEntity<>(new APIResponse<>(true, "Đặt vé thành công!", ticket, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping("/api/tickets/my")
    public ResponseEntity<APIResponse<List<Ticket>>> getMyTickets(String username) {
        List<Ticket> tickets = ticketService.getMyTickets(username);
        return new ResponseEntity<>(new APIResponse<>(true, "Xem lịch sử vé của user hiện tại", tickets, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/api/admin/tickets")
    public ResponseEntity<APIResponse<List<Ticket>>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return new ResponseEntity<>(new APIResponse<>(true, "Danh sách toàn bộ vé đã đặt", tickets, HttpStatus.OK), HttpStatus.OK);
    }
}
