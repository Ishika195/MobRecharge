package com.example.MobRecharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MobRecharge.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(int id);

}
