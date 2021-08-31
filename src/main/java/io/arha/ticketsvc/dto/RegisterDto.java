package io.arha.ticketsvc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterDto {
	@NotNull
	@NotBlank
	@Email
	private String username;

	@NotBlank
	@NotNull
	@Size(min = 2, max = 20)
	private String name;

	@NotBlank
	@NotNull
	@Size(min = 4, max = 20)
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
