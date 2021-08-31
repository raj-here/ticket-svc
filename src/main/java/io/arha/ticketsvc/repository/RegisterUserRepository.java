package io.arha.ticketsvc.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.arha.ticketsvc.entity.RegisterUser;

@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUser, Long> {
	RegisterUser findByRegisterLinkIdAndExpiredOnGreaterThan(String registerLinkId, Date expiredOn);
}
