package io.arha.ticketsvc.service;

import io.arha.ticketsvc.entity.RegisterUser;

public interface EmailService {
  
	void sendRegisterUserEmail(RegisterUser registerUser);
	
}
