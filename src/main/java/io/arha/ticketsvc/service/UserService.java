package io.arha.ticketsvc.service;

import java.util.List;

import javax.validation.Valid;

import io.arha.ticketsvc.dto.UserDto;
import io.arha.ticketsvc.dto.UserSubmitDto;

public interface UserService {

	List<UserDto> findAll();

	UserDto findAllById(Long id);

	void save(UserSubmitDto userSubmitDto);

	void update(Long id, @Valid UserSubmitDto userSubmitDto);

	void delete(Long id);

}
