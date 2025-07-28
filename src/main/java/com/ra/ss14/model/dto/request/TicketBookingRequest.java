package com.ra.ss14.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketBookingRequest {
    private Long showtimeId;
    private String seatNumber;
    private BigDecimal price;
}
