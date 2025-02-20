package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RecordController {
	
	@GetMapping("/register")
	public String getRegister() {
		return "";
	}
	
	
	@GetMapping("/list")
	public String getviewlist() {
		return "";
	}
	
	
}
