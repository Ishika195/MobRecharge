package com.example.MobRecharge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MobRecharge.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserId(Long id);

	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
