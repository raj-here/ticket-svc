package io.arha.ticketsvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.arha.ticketsvc.dto.LoginDto;
import io.arha.ticketsvc.dto.TokenDto;
import io.arha.ticketsvc.service.TicketUserDetailsService;
import io.arha.ticketsvc.util.JwtUtil;

@RestController
@RequestMapping("/home")
/**
 * This Home contoller of application
 * 
 * 
 * @author ashish
 *
 */
public class HomeController {

	@Autowired
	private TicketUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * 
	 * @param loginDto
	 * This will take username and password 
	 * validate username and pass and genrate token
	 * @return TokenDto
	 */
	@PostMapping("/auth")
	public TokenDto login(@Valid @RequestBody LoginDto loginDto) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		
		UserDetails userDetail = userDetailsService.loadUserByUsername(loginDto.getUsername());
		return new TokenDto(jwtUtil.generateToken(userDetail));
	}

}
