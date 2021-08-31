package io.arha.ticketsvc.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.arha.ticketsvc.dto.UserDto;
import io.arha.ticketsvc.dto.UserSubmitDto;
import io.arha.ticketsvc.entity.Role;
import io.arha.ticketsvc.entity.User;
import io.arha.ticketsvc.entity.UserRole;
import io.arha.ticketsvc.enums.RoleType;
import io.arha.ticketsvc.repository.RoleRepository;
import io.arha.ticketsvc.repository.UserRepository;
import io.arha.ticketsvc.repository.UserRoleRepository;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<UserDto> findAll() {
		return userRepository.findAll().stream().map(this::mapUser).collect(Collectors.toList());
//		List<UserDto> userDtos = new ArrayList<>();
//		for (Iterator<User> iterator = uses.iterator(); iterator.hasNext();) {
//			User user = 
//			List<UserRole> userRoles = userRoleRepository.findAllByUser(user);
//			List<RoleType> roles = userRoles.stream().map(userRole -> userRole.getRole().getName())
//					.collect(Collectors.toList());
//			userDtos.add(convert(iterator.next()));
//		}
//		return userDtos;
	}

	@Override
	public UserDto findAllById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return mapUser(userOptional.get());
		} else {
			throw new RuntimeException("User not found");
		}

	}

	private UserDto mapUser(User user) {
		List<UserRole> userRoles = userRoleRepository.findAllByUser(user);
		List<RoleType> roles = userRoles.stream().map(userRole -> userRole.getRole().getName())
				.collect(Collectors.toList());
		return new UserDto(user.getId(), user.getUsername(), user.getName(), user.getEnable(), roles);
	}

	@Override
	public void save(UserSubmitDto userSubmitDto) {
		User user = new User();
		user.setUsername(userSubmitDto.getUsername());
		user.setName(userSubmitDto.getName());
		user.setEnable(true);
		user.setPassword(UUID.randomUUID().toString());
		userRepository.save(user);
		List<Role> roles = roleRepository.findAllByNameIn(userSubmitDto.getRoles());
		for (Role role : roles) {
			UserRole uRole2 = new UserRole(role, user);
			userRoleRepository.save(uRole2);
		}

	}

	@Override
	public void update(Long id, @Valid UserSubmitDto userSubmitDto) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<UserRole> roles = userRoleRepository.findAllByUser(user);
			userRoleRepository.deleteAll(roles);
			user.setUsername(userSubmitDto.getUsername());
			user.setName(userSubmitDto.getName());
			userRepository.save(user);
			List<Role> addRoles = roleRepository.findAllByNameIn(userSubmitDto.getRoles());
			for (Role role : addRoles) {
				UserRole uRole2 = new UserRole(role, user);
				userRoleRepository.save(uRole2);
			}
		} else {
			throw new RuntimeException("User not found");
		}

	}

	@Override
	public void delete(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setEnable(false);
			userRepository.save(user);
		} else {
			throw new RuntimeException("User not found");
		}

	}

}
