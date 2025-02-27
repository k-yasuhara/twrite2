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
	//urlの手打ち省略
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
			@Valid @ModelAttribute Admin inputAdmin,
			Errors errors,
			Model m) {
		
		//入力エラーチェック
		if (errors.hasErrors()) {
			m.addAttribute("admin", inputAdmin);
			return "login";
		}
		
		//idとpassチェック
		if (!service.loginByIdAndPass(inputAdmin.getLoginId(), inputAdmin.getLoginPass())) {
			m.addAttribute("errorMsg",
					"不正なログインです");
			m.addAttribute("admin", inputAdmin);
			return "login";
		}
		
		//sessionにアカウント情報を格納
		Admin admin =service.selectByIdAndPass(inputAdmin.getLoginId());
		session.setAttribute("loginId", admin.getLoginId());
		session.setAttribute("loginName", admin.getName());
		session.setAttribute("loginNum", admin.getId());
		return "redirect:/Twrite/top";
	}
	
	@GetMapping("/logout")
	public String getMethodName() {
		session.invalidate();
		return "redirect:/";
	}
	

}
