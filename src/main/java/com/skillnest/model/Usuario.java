package com.skillnest.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password; // bcrypt

	@Column(nullable = false)
	private String role; // ROLE_USER, ROLE_ADMIN

	// constructores
	public Usuario() {
	}

	public Usuario(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	// getters y setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
