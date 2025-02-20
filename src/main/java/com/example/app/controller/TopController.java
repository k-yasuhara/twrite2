package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/top")
@RequiredArgsConstructor
public class TopController {
	
	private final HttpSession session;
	
	@GetMapping
	public String getTop(Model m) {
		
		
		
		
		
		
		return "top";
	}

	
	
}
