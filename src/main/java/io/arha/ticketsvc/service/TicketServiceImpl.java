package io.arha.ticketsvc.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.arha.ticketsvc.dto.TicketDto;
import io.arha.ticketsvc.dto.TicketSubmitionDto;
import io.arha.ticketsvc.entity.Ticket;
import io.arha.ticketsvc.entity.User;
import io.arha.ticketsvc.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private TicketUserDetailsService userDetailsService;

	@Override
	/**
	 * get tickets created by me
	 */
	public List<TicketDto> getMyTickets() {
		return ticketRepository.findAll().stream().map(ticket -> {
			TicketDto dto = new TicketDto();
			dto.setId(ticket.getId());
			dto.setTicketSubject(ticket.getTicketSubject());
			dto.setCreatedBy(ticket.getCreatedBy().getName());
			dto.setDateCreated(ticket.getCreatedDate().toString());
			return dto;
		}).collect(Collectors.toList());
	}
	/**
	 * get ticket by Id
	 */
	@Override
	public TicketSubmitionDto get(Long id) {
		// TODO admin , user created , working on it
		Optional<Ticket> optional = ticketRepository.findById(id);
		if (optional.isPresent()) {
			Ticket ticket = optional.get();
			TicketSubmitionDto dto = new TicketSubmitionDto();
			dto.setTicketSubject(ticket.getTicketSubject());
			dto.setDescription(ticket.getTicketDescription());
			dto.setCreatedBy(ticket.getCreatedBy().getName());
			User workedBy = ticket.getWorkedBy();
			if (workedBy == null) {
				dto.setWorkedBy("Not yet started.");
			} else {
				dto.setWorkedBy(workedBy.getName());
			}
			if (ticket.getLastUpdated() != null) {
				dto.setLastUpdated(ticket.getLastUpdated().toString());
			}
			dto.setDateCreated(ticket.getCreatedDate().toString());
			dto.setType(ticket.getTicketType());
			return dto;
		} else {
			throw new RuntimeException("Record not found");
		}
	}
	/** 
	 * save ticket
	 */
	@Override
	public void save(TicketSubmitionDto ticketSubmitionDto) {
		Ticket ticket = new Ticket();
		ticket.setTicketSubject(ticketSubmitionDto.getTicketSubject());
		ticket.setTicketDescription(ticketSubmitionDto.getDescription());
		ticket.setTicketType(ticketSubmitionDto.getType());
		ticket.setCreatedBy(userDetailsService.currentUser());
		ticketRepository.save(ticket);
	}

	 @Override
	public void update(Long id, TicketSubmitionDto ticketSubmitionDto) {
		// user created
		Optional<Ticket> optional = ticketRepository.findById(id);
		if (optional.isPresent()) {
			Ticket ticket = optional.get();
			User currentUser=userDetailsService.currentUser();
			if(!currentUser.equals(ticket.getCreatedBy())) {
				throw new RuntimeException("User does't have permissions to update it.");
			}
			ticket.setTicketSubject(ticketSubmitionDto.getTicketSubject());
			ticket.setTicketDescription(ticketSubmitionDto.getDescription());
			ticket.setTicketType(ticketSubmitionDto.getType());
			ticket.setCreatedBy(userDetailsService.currentUser());
			ticketRepository.save(ticket);
		} else {
			throw new RuntimeException("Record not found");
		}
	}

	@Override
	public void delete(Long id) {
		// TODO admin , user created
		Optional<Ticket> optional = ticketRepository.findById(id);
		if (optional.isPresent()) {
			Ticket ticket = optional.get();
			ticketRepository.delete(ticket);
		} else {
			throw new RuntimeException("Record not found");
		}
	}

}
