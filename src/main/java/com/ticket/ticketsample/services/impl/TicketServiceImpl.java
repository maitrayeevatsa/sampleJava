package com.ticket.ticketsample.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ticket.ticketsample.model.Ticket;
import com.ticket.ticketsample.repository.TicketRepository;
import com.ticket.ticketsample.services.TicketService;

import lombok.Data;

import com.ticket.ticketsample.exception.ResourceNotFoundException;

@Service
@Data
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        super();
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Ticket updateTicket(Ticket ticket, long id) {

        // we need to check whether Ticket with given id exist in DB or not
        Ticket existingTicket = ticketRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ticket", "Id", id));

        existingTicket.setTitle(ticket.getTitle());
        existingTicket.setSubject(ticket.getSubject());
        existingTicket.setStatus(ticket.getStatus());
        // save existing ticket to DB
        ticketRepository.save(existingTicket);
        return existingTicket;
    }

    @Override
    public void deleteTicket(long id) {
        // check whether a ticket exist in a DB or not
        ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket", "Id", id));
        ticketRepository.deleteById(id);
    }

}
