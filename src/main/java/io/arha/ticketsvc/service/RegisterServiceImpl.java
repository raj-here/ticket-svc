package io.arha.ticketsvc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.arha.ticketsvc.dto.RegisterDto;
import io.arha.ticketsvc.entity.RegisterUser;
import io.arha.ticketsvc.entity.User;
import io.arha.ticketsvc.repository.RegisterUserRepository;
import io.arha.ticketsvc.repository.UserRepository;
import io.arha.ticketsvc.util.Util;

@Service
public class RegisterServiceImpl implements RegisterServcie {

	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private Util  util;
	@Autowired
	private RegisterUserRepository registerUserRepository;
	@Autowired
	private EmailService emailService;
	
	
	@Override
	public void registerNewUser(RegisterDto register) {
		long count =userRepository.countByUsername(register.getUsername());
		if(count<=0) {
			RegisterUser registerUser= new RegisterUser();
			registerUser.setName(register.getName());
			registerUser.setUsername(register.getUsername());
			registerUser.setPassword(register.getPassword());
			registerUser.setRegisterLinkId(util.getUniqueCode());
			registerUser.setExpiredOn(util.addDays(2));
			registerUserRepository.save(registerUser); 
			emailService.sendRegisterUserEmail(registerUser);
		}else {
			throw new RuntimeException("User already exits!");
		} 
	}


	@Override
	public void findAndVerifyUserByLinkId(String registerLinkId) { 
		RegisterUser registerUser=registerUserRepository.findByRegisterLinkIdAndExpiredOnGreaterThan(registerLinkId, new Date());
		if(registerUser==null) { 
			throw new RuntimeException("This link is either used/expired or wrong.");
		}else {
			long count =userRepository.countByUsername(registerUser.getUsername());
			if(count<=0) {
				User user= new User();
				user.setName(registerUser.getName());
				user.setUsername(registerUser.getUsername());
				user.setEnable(true); 
				user.setPassword(registerUser.getPassword());
				userRepository.save(user);
			}else {
				throw new RuntimeException("User already exits!");
			}  
		}
	}

}
