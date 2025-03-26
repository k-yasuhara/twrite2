package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

		Integer recordsId = service.addRecord(recordForm);

		if (!recordForm.getSymptoms().isEmpty()) {
			List<Integer> symptomsList = recordForm.getSymptoms();
			for (Integer symptoms : symptomsList) {
				service.addSymptoms(recordsId, symptoms);
			}
		}

		return "redirect:/Twrite/top";
	}

	@GetMapping("/list")
	public String getviewlist(Model m) {
		m.addAttribute("title", "全ての記録");
		m.addAttribute("recordList", service.getRecordsWithDetails());

		//承認状況によって、modal表示するコード
		//TODO
		return "/record/list";
	}

	@PostMapping("/list")
	public String postMethodName(
			@RequestParam Integer loginNum,
			@RequestParam(required = false) Integer approval,
			Model m) {

		if (approval == null) {
			m.addAttribute("title", "未承認");
			m.addAttribute("recordList", service.getUnapprovedRecords(loginNum, approval));
		} else if (approval == 2) { //差し戻しの場合
			m.addAttribute("title", "差し戻し");
			m.addAttribute("recordList", service.getUnapprovedRecords(loginNum, approval));
		} else if (approval == 1) {
			m.addAttribute("title", "承認済み");
			m.addAttribute("recordList", service.getUnapprovedRecords(loginNum, approval));			
		} else {
			m.addAttribute("recordList", service.getRecordsWithDetails());
		}

		return "/record/list";
	}

}
