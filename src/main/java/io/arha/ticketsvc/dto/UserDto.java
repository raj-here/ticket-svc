package io.arha.ticketsvc.dto;

import java.util.List;

import io.arha.ticketsvc.enums.RoleType;

public class UserDto {
	private Long id;
	private String username;
	private String name;
	private boolean enable;
	private List<RoleType> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<RoleType> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleType> roles) {
		this.roles = roles;
	}

	public UserDto(Long id, String username, String name, boolean enable, List<RoleType> roles) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.enable = enable;
		this.roles = roles;
	}

	public UserDto() {

	}
}
