package io.arha.ticketsvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.arha.ticketsvc.enums.RoleType;

@Entity(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated (EnumType.STRING)
	@Column(name="name" ,nullable=false,unique=true)
	private RoleType name;
	
	@Column(name="display_name" ,nullable=false,unique=true)
	private String displayName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleType getName() {
		return name;
	}

	public void setName(RoleType name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Role(RoleType name, String displayName) {
		super();
		this.name = name;
		this.displayName = displayName;
	}
	
	public Role() {
		
	}
}
