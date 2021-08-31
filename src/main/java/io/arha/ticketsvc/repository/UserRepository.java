package io.arha.ticketsvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.arha.ticketsvc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   long countByUsername(String username);
   User findByUsername(String username);
}
