package com.desafioBack.criptografia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.desafioBack.criptografia.entites.User;

public interface UserRepository extends JpaRepository<User, Long>{
	 boolean existsByGmail(String gmail);
	 UserDetails findByGmail(String gmail);
}
