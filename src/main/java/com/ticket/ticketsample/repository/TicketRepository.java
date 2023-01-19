package com.ticket.ticketsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ticket.ticketsample.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
