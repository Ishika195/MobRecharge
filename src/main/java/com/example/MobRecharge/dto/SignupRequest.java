package com.example.MobRecharge.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignupRequest {
	@NotBlank(message = "Username is mandatory")
	private String username;
	
	@NotBlank(message = "Email is mandatory")
	@Email
	private String email;
	
	@NotNull
	private Set<String> role;
	
	@NotBlank(message = "Please enter a password")
	@Size(min = 8, max = 20)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return this.role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
}
