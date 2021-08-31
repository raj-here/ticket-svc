package io.arha.ticketsvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.arha.ticketsvc.entity.Role;
import io.arha.ticketsvc.entity.User;
import io.arha.ticketsvc.entity.UserRole;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	List<UserRole> findAllByUser(User user);

	List<UserRole> findAllByRole(Role role);

}
