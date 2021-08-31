package io.arha.ticketsvc.dto;

import java.util.List;

public class UserWrapperDto {
	private List<UserDto> data;

	public List<UserDto> getData() {
		return data;
	}

	public void setData(List<UserDto> data) {
		this.data = data;
	}

	public UserWrapperDto(List<UserDto> data) {
		super();
		this.data = data;
	}

	public UserWrapperDto() {

	}
}
