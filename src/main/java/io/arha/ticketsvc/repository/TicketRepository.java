package io.arha.ticketsvc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.arha.ticketsvc.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findAllByTicketSubject(String ticketSubject);
	List<Ticket> findAllByTicketSubjectAndId(String ticketSubject,Long id);
	
	Optional<Ticket> findById(Long id);
	
}
