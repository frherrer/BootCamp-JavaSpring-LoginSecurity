package com.skillnest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//Aplicacion para crear contraseñas por defecto en BD
public class CreaHash {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin123"));
	}
}