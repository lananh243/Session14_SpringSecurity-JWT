package com.ra.ss14.repository;

import com.ra.ss14.model.entity.Ticket;
import com.ra.ss14.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByUser(User user);
}
