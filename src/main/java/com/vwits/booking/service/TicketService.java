package com.vwits.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.vwits.booking.model.Ticket;
import com.vwits.booking.repository.TicketRepository;

@Service
public class TicketService {
	
	private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){

        this.ticketRepository = ticketRepository;
    }
    
    public List<Ticket> getTickets(String emailId){
      return  ticketRepository.findTicketsByEmail(emailId);
    }
    
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    
    public Ticket getTicketsByEmailAndId(String emailId,int id){
        return  ticketRepository.findTicketByEmailAndId(emailId,id);
      }
    
 
	public Ticket findById(long id) {
		Optional<Ticket> optionalTicket = ticketRepository.findById(id);
		return optionalTicket.isPresent() ? optionalTicket.get() : null;
	}

	public Ticket update(Ticket ticket) {
		Ticket ticket2 = findById(ticket.getId());
		if(ticket2 != null) {
			BeanUtils.copyProperties(ticket, ticket2,"user");
			ticketRepository.save(ticket2);
		}
		return ticket;
		
	}
	
	public void delete(long id) {
		ticketRepository.deleteById(id);
	}
}
