package io.arha.ticketsvc.service;

import io.arha.ticketsvc.dto.RegisterDto;

public interface RegisterServcie {
  
	void registerNewUser(RegisterDto register);

	void findAndVerifyUserByLinkId(String registerLinkId);
 
	
}
