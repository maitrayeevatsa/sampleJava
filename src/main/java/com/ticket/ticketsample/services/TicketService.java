package com.ticket.ticketsample.services;

import java.util.List;
import com.ticket.ticketsample.model.Ticket;

public interface TicketService {
    Ticket saveTicket(Ticket ticket);

    List<Ticket> getAllTickets();

    Ticket getTicketById(long id);

    Ticket updateTicket(Ticket ticket, long id);

    void deleteTicket(long id);
}
