package com.ticket.ticketsample.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ticket.ticketsample.model.Ticket;
import com.ticket.ticketsample.services.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private static final Logger logger = LogManager.getLogger(TicketController.class);
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        super();
        this.ticketService = ticketService;
    }

    // build create ticket REST API
    @PostMapping("/create")
    public ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket) {
        try {
            logger.info("Creating A ticket!!");
            return new ResponseEntity<Ticket>(ticketService.saveTicket(ticket), HttpStatus.CREATED);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to create records", ex);
        }
    }

    // build get all tickets REST API
    @GetMapping("/findAll")
    public List<Ticket> getAllTickets() {
        try {
            logger.info("Finding All ticket!!");
            return ticketService.getAllTickets();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to find all records", ex);
        }
    }

    // built get tickets by id REST API
    @GetMapping("find/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") long ticketId) {
        try {
            logger.info("Finding given ticket!!");
            return new ResponseEntity<Ticket>(ticketService.getTicketById(ticketId), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Failed to find records", ex);
        }
    }

    // build update ticket REST API
    @PutMapping("update/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") long id, @RequestBody Ticket ticket) {
        try {
            logger.info("Updating mentioned ticket!!");
            return new ResponseEntity<Ticket>(ticketService.updateTicket(ticket, id), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to update record", ex);
        }
    }

    // build delete ticket REST API
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable("id") long id) {
        try {
            logger.info("Deleting mentioned ticket!!");
            // delete ticket from DB
            ticketService.deleteTicket(id);
            return new ResponseEntity<String>("Ticket deleted successfully!.", HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Failed to delete record", ex);
        }
    }

}
