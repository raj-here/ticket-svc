package io.arha.ticketsvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.arha.ticketsvc.entity.UserRole;
import io.arha.ticketsvc.repository.UserRepository;
import io.arha.ticketsvc.repository.UserRoleRepository;

@Service
public class TicketUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	/**
	 * load user and its roles from database
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		io.arha.ticketsvc.entity.User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		} else if (!user.getEnable()) {
			throw new RuntimeException("User is disabled. Please contact to admin.");
		} else {
			// get Role from data base
			List<UserRole> userRoles = userRoleRepository.findAllByUser(user);
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			for (UserRole userRole : userRoles) {
				authorities.add(new SimpleGrantedAuthority(userRole.getRole().getName().name()));
			}
			return new User(user.getUsername(), user.getPassword(), authorities);
		}
	}

	/**
	 * get logged in user
	 * 
	 * @return
	 */
	io.arha.ticketsvc.entity.User currentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		return userRepository.findByUsername(user.getUsername());
	}

}
