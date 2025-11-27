package com.skillnest.config;

import com.skillnest.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@SuppressWarnings("deprecation")
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(passwordEncoder());

		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	// Configuración SecurityFilterChain
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authenticationProvider(daoAuthenticationProvider()) //
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers("/css/**", "/js/**", "/images/**", "/login", "/error")
								.permitAll().requestMatchers("/admin/**").hasRole("ADMIN") // usa ROLE_ADMIN en BD
								.requestMatchers("/perfil/**").authenticated().anyRequest().permitAll())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/panel", true).permitAll())
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll())
				.csrf(csrf -> csrf.disable()); // solo para desarrollo

		return http.build();
	}
}
