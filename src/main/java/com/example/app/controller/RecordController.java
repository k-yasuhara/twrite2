package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		m.addAttribute("topic", "新規入力");
		RecordDB record = new RecordDB();
		m.addAttribute("record", record);
		m.addAttribute("staffType", service.getStaffType());
		m.addAttribute("patientType", service.getPatientType());
		m.addAttribute("symptomType", service.getSymptomType());

		return "record/add";
	}

	@PostMapping("/register")
	public String postRegister(
			@ModelAttribute RecordDB recordForm,
			Model m) {
		m.addAttribute("topic", "新規入力");
		Integer recordsId = service.addRecord(recordForm);

		if (!recordForm.getSymptoms().isEmpty()) {
			List<Integer> symptomsList = recordForm.getSymptoms();
			for (Integer symptoms : symptomsList) {
				service.addSymptoms(recordsId, symptoms);
			}
		} else { //症状種別で何も選択されてない場合に一覧画面に表示されないため、該当なしで症状種別を入力
			service.addSymptoms(recordsId, 0);
		}

		return "redirect:/Twrite/top";
	}

	@GetMapping("/list")
	public String getViewList(Model m) {
		m.addAttribute("title", "全ての記録");
		m.addAttribute("recordList", service.getRecordsWithDetails());
		return "record/list";
	}

	@PostMapping("/list")
	public String postViewList(
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

		return "record/list";
	}

	@GetMapping("/view")
	public String getView(
			@RequestParam int id, @RequestParam String title,
			Model m) {
		m.addAttribute("topic", "記録閲覧");
		RecordDB record = service.findRecordsById(id);
		m.addAttribute("title", title);
		m.addAttribute("record", record);
		m.addAttribute("staffType", service.getStaffType());
		m.addAttribute("patientType", service.getPatientType());
		m.addAttribute("symptomType", service.getSymptomType());
		return "record/view";
	}

	@GetMapping("/edit")
	public String getEdit(@RequestParam int id,
			Model m) {
		m.addAttribute("topic", "記録編集");
		RecordDB record = service.findRecordsById(id);
		m.addAttribute("record", record);
		m.addAttribute("staffType", service.getStaffType());
		m.addAttribute("patientType", service.getPatientType());
		m.addAttribute("symptomType", service.getSymptomType());
		return "record/add";
	}

	@PostMapping("/edit")
	public String postEdit(
			@ModelAttribute RecordDB recordForm,
			Model m) {
		service.updateRecords(recordForm);
		service.updateSymptoms(recordForm);
		return "redirect:/Twrite/list";
	}

	@GetMapping("/permit")
	public String getPermit(@RequestParam int id,
			RedirectAttributes ra) {
		service.updateApprovalStatus(id, 1);
		ra.addFlashAttribute("modalTitle", "承認状況");
		ra.addFlashAttribute("modalMsg", "承認しました");
		return "redirect:/Twrite/list";
	}

	@GetMapping("/remand")
	public String getRemand(@RequestParam int id,
			RedirectAttributes ra) {
		service.updateApprovalStatus(id, 2);
		ra.addFlashAttribute("modalTitle", "承認状況");
		ra.addFlashAttribute("modalMsg", "差し戻しました");
		return "redirect:/Twrite/list";
	}

	@GetMapping("/delete")
	public String getDelete(Model m) {
		m.addAttribute("title", "削除");
		m.addAttribute("recordList", service.getRecordsWithDetails());
		return "record/list";
	}

	@GetMapping("/delete/{id}")
	public String getDeleteById(
			@PathVariable int id,
			Model m) {
		m.addAttribute("topic", "記録削除");
		RecordDB record = service.findRecordsById(id);
		m.addAttribute("title", "記録削除");
		m.addAttribute("deleteMsg", "この記録を削除しますか？内容を確認してください");
		m.addAttribute("record", record);
		m.addAttribute("staffType", service.getStaffType());
		m.addAttribute("patientType", service.getPatientType());
		m.addAttribute("symptomType", service.getSymptomType());
		return "record/view";
	}

	@PostMapping("/delete/{id}")
	public String postDeleteById(
			@PathVariable int id, RedirectAttributes ra) {
		service.deleteRecords(id);
		ra.addFlashAttribute("modalTitle", "記録の削除");
		ra.addFlashAttribute("modalMsg", "記録を削除しました");
		return "redirect:/Twrite/list";
	}

}
