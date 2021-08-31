package io.arha.ticketsvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.arha.ticketsvc.dto.TicketDto;
import io.arha.ticketsvc.dto.TicketSubmitionDto;
import io.arha.ticketsvc.dto.TicketWrapperDto;
import io.arha.ticketsvc.service.TicketService;

@RestController
@RequestMapping("/ticket")
/**
 * All oprations realted to ticket
 * 
 * @author ashish
 *
 */
public class TicketController { // C-R-U-D
	@Autowired
	private TicketService ticketService;

	/**
	 * get all tickets created by me
	 * 
	 * @return
	 */
	@GetMapping("")
	public TicketWrapperDto getTicketCreatedByUserList() {
		List<TicketDto> data = ticketService.getMyTickets();
		TicketWrapperDto ticketWrapperDto = new TicketWrapperDto();
		ticketWrapperDto.setData(data);
		return ticketWrapperDto;
	}

	/**
	 * save a new ticket
	 * 
	 * @param ticketSubmitionDto
	 */
	@PostMapping("")
	public void save(@Valid @RequestBody TicketSubmitionDto ticketSubmitionDto) {
		ticketService.save(ticketSubmitionDto);
	}

	/**
	 * get details of a ticket by id
	 */
	@GetMapping("/{id}")
	public TicketSubmitionDto read(@PathVariable Long id) {
		return ticketService.get(id);
	}

	/**
	 * update a ticket
	 * 
	 * @param id
	 * @param ticketSubmitionDto
	 */
	@PutMapping("/{id}")
	public void update(@PathVariable Long id, @Valid @RequestBody TicketSubmitionDto ticketSubmitionDto) {
		ticketService.update(id, ticketSubmitionDto);
	}

	/**
	 * delete a ticket
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		ticketService.delete(id);
	}

}
