package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Admin;
import com.example.app.service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class AuthController {

	private final AdminService service;
	private final HttpSession session;

	@GetMapping
	public String startLogin() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String getLogin(Model m) {
		m.addAttribute("admin", new Admin());
		return "login";
	}

	@PostMapping("/login")
	public String postLogin(
			@Valid @ModelAttribute Admin admin,
			Errors errors,
			Model m) {

		if (errors.hasErrors()) {
			m.addAttribute("admin", admin);
			return "login";
		}

		if (!service.loginByIdAndPass(admin.getLoginId(), admin.getLoginPass())) {
			m.addAttribute("errorMsg",
					"不正なログインです");
			m.addAttribute("admin", admin);
			return "login";
		}

		session.setAttribute("loginId", admin.getLoginId());
		return "redirect:/top";
	}
	
	@GetMapping("/logout")
	public String getMethodName() {
		session.invalidate();
		return "redirect:/";
	}
	

}
