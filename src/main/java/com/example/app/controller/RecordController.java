package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.RecordDB;
import com.example.app.service.RecordService;

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
		m.addAttribute("patientType", service.getPatientType());
		m.addAttribute("symptomType", service.getSymptomType());

		return "/record/add";
	}

	@PostMapping("/register")
	public String postRegister(
			@ModelAttribute RecordDB recordForm,
			Model m) {
				
		System.out.println(recordForm);
		service.addRecord(recordForm);
		return "redirect:/Twrite/top";
	}

	@GetMapping("/list")
	public String getviewlist() {
		return "";
	}

}
