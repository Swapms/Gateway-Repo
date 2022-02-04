package com.vwits.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vwits.booking.model.ApiResponse;
import com.vwits.booking.model.Ticket;
import com.vwits.booking.service.TicketService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v2/users")
public class TicketController {
	
	@Autowired
    private TicketService ticketService;
	
	
	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@PostMapping
	public ApiResponse<Ticket> createTicket(@RequestBody Ticket ticket) {
		return new ApiResponse<>(HttpStatus.OK.value(), "Ticket saved successfully.",ticketService.createTicket(ticket));

	}
	
	@GetMapping("/{email}")
    public ApiResponse<List<Ticket>> getTickets(@PathVariable String email){
		return new ApiResponse<>(HttpStatus.OK.value(), "Tickets list fetched successfully.",ticketService.getTickets(email));
    }

	@GetMapping("/ticket/{id}")
    public ApiResponse<Ticket> getTicketById(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Ticket fetched successfully.",ticketService.findById(id));
    }
	
	
	@PutMapping("/{id}")
    public ApiResponse<Ticket> update(@RequestBody Ticket ticket) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Ticket updated successfully.",ticketService.update(ticket));
    }
	
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
    	ticketService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Ticket deleted successfully.", null);
    } 

	
	/*@GetMapping
    public List<Ticket> getTickets(@RequestParam("email") String email) {
		System.out.println(email);
        return ticketService.getTickets(email);
    }*/
	

}
