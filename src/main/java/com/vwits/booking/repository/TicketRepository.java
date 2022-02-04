package com.vwits.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vwits.booking.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query("select a from Ticket a where emailId = ?1")
	List<Ticket> findTicketsByEmail(String emailId);
	
	@Query("select a from Ticket a where user_email = ?1 and id = ?2")
	Ticket findTicketByEmailAndId(String emailId, int tktId);

}
