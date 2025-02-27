package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.RecordDB;
import com.example.app.service.RecordService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/Twrite")
@RequiredArgsConstructor
public class RecordController {
	
	private final RecordService service;
	
	@GetMapping("/register")
	public String getRegister(Model m) {
		RecordDB record = new RecordDB();
		
		m.addAttribute("record", record);
		m.addAttribute("staffType", service.getStaffType());
		return "/record/add";
	}
	
	@PostMapping("/register")
	public String postRegister(
			@Valid RecordDB record,
			Errors errors,
			Model m) {
		
		
		return "/record/add";
	}
	

	@GetMapping("/list")
	public String getviewlist() {
		return "";
	}

}
