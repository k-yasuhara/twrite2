package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.service.RecordDBService;

import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/top")
@RequiredArgsConstructor
public class TopController {
	
	private final RecordDBService service;
	
	@GetMapping
	public String getTop(Model m) {
		
		//相談件数
		m.addAttribute("recordCount", service.getCountsForTodayAndYesterday());
		
		//相談傾向
		
		
		//相談件数グラフ
		m.addAttribute("thisWeeklyCounts", service.getCountsForThisWeek());
		m.addAttribute("lastWeeklyCounts", service.getCountsForLastWeek());
		
		return "top";
	}

	
	
}
