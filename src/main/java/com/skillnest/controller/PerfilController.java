package com.skillnest.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.skillnest.service.CustomUserDetails;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilController {

	@GetMapping("/perfil")
	public String perfil(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("role", userDetails.getAuthorities());
		return "perfil";
	}
}
