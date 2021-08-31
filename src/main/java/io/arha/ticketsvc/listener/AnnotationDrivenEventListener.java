package io.arha.ticketsvc.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import io.arha.ticketsvc.entity.Role;
import io.arha.ticketsvc.entity.User;
import io.arha.ticketsvc.entity.UserRole;
import io.arha.ticketsvc.enums.RoleType;
import io.arha.ticketsvc.repository.RoleRepository;
import io.arha.ticketsvc.repository.UserRepository;
import io.arha.ticketsvc.repository.UserRoleRepository;

@Component
public class AnnotationDrivenEventListener {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		if (applicationContext.getParent() == null) { // Root Parent initialized
			System.out.println("Application started.");
			addRolesAndUserOnInit();

		}
	}

	private void addRolesAndUserOnInit() {
		Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN);
		if (adminRole == null) {
			adminRole = new Role(RoleType.ROLE_ADMIN, "Admin");
			roleRepository.save(adminRole);
		}
		Role userRole = roleRepository.findByName(RoleType.ROLE_USER);
		if (userRole == null) {
			userRole = new Role(RoleType.ROLE_USER, "User");
			roleRepository.save(userRole);
		}
		addUser(adminRole, userRole);
	}

	private void addUser(Role adminRole, Role userRole) {
		User user = userRepository.findByUsername("admin@ticketapp.com");
		if (user == null) {
			user = new User();
			user.setEnable(true);
			user.setUsername("admin@ticketapp.com");
			user.setName("Admin user");
			user.setPassword("XYz");
			userRepository.save(user);
			UserRole uRole = new UserRole(adminRole, user);
			UserRole uRole2 = new UserRole(userRole, user);
			userRoleRepository.save(uRole);
			userRoleRepository.save(uRole2);
		}
	}
}
