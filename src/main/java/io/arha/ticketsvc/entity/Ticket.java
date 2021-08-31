package io.arha.ticketsvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import io.arha.ticketsvc.enums.TicketType;

@Entity(name = "tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "ticket_subject" ,nullable=false)
	private String ticketSubject;
	
	@Column(name = "ticket_description" )
	private String ticketDescription;

	@Enumerated (EnumType.STRING)
	@Column(name = "ticket_type" ,nullable=false)
	private TicketType ticketType;
	 
	@JoinColumn(name="user_created_by_id")
	@ManyToOne
	private User createdBy;
	

	@JoinColumn(name="user_worked_by_id" )
	@ManyToOne
	private User workedBy;
	

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "last_updated")
	private Date lastUpdated;

	public Ticket() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTicketSubject() {
		return ticketSubject;
	}

	public void setTicketSubject(String ticketSubject) {
		this.ticketSubject = ticketSubject;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getWorkedBy() {
		return workedBy;
	}

	public void setWorkedBy(User workedBy) {
		this.workedBy = workedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	@PrePersist
	void saveDateCreated() {
		this.createdDate= new Date();
	}
	
	@PreUpdate
	void savelastUpdatedDate() {
		this.lastUpdated= new Date();
	}
}
