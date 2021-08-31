package io.arha.ticketsvc.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import io.arha.ticketsvc.enums.TicketType;

public class TicketSubmitionDto {

	@NonNull
	@NotBlank
	@Size(max = 100)
	private String ticketSubject;
	@NotNull
	private TicketType type;
	private String description;
	private String link;
	private String createdBy;
	private String workedBy ;
	public String getWorkedBy() {
		return workedBy;
	}

	public void setWorkedBy(String workedBy) {
		this.workedBy = workedBy;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	private String dateCreated;
	private String lastUpdated;

	public String getTicketSubject() {
		return ticketSubject;
	}

	public void setTicketSubject(String ticketSubject) {
		this.ticketSubject = ticketSubject;
	}

	public TicketType getType() {
		return type;
	}

	public void setType(TicketType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public TicketSubmitionDto() {
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "TicketSubmitionDto [ticketSubject=" + ticketSubject + ", type=" + type + ", description=" + description
				+ "]";
	}

}
