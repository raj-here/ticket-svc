package io.arha.ticketsvc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginDto {
	@NotBlank
	@NotNull
	@Email
	private String username;
	@NotBlank
	@Size(min=2)
	private String password;

	public LoginDto() {

	}

	public LoginDto(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
