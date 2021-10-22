package com.example.MobRecharge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MobRecharge.entity.Role;
import com.example.MobRecharge.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	public void saveUserRole(Role role) {
		roleRepository.save(role);
	}
}
