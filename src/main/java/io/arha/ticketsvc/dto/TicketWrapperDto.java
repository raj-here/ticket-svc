package io.arha.ticketsvc.dto;

import java.util.List;

public class TicketWrapperDto {
	private List<TicketDto> data;

	public TicketWrapperDto() {
	}

	public List<TicketDto> getData() {
		return data;
	}

	public void setData(List<TicketDto> data) {
		this.data = data;
	}
}
