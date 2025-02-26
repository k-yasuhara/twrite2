package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.dto.SymptomsCount;
import com.example.app.service.RecordService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/top")
@RequiredArgsConstructor
public class TopController {

	private final RecordService service;

	@GetMapping
	public String getTop(Model m) {

		//相談件数
		int todayCount = service.getCountsForTodayAndYesterday()[0];
		int yesterdayCount = service.getCountsForTodayAndYesterday()[1];

		m.addAttribute("todayCount", todayCount);
		if(todayCount - yesterdayCount == 0) {
			m.addAttribute("prevDiff", "±0");			
		}else {
			m.addAttribute("prevDiff", todayCount - yesterdayCount);			
		}

		//相談傾向
		List<SymptomsCount> todaySymptoms = service.getTopSymptomsToday();
		System.out.println(todaySymptoms);
		if (todaySymptoms != null) {
			m.addAttribute("todaySymptoms", todaySymptoms);
			
		} else {
			m.addAttribute("msg", "本日のデータがありません");
		}

		//相談件数グラフ
		m.addAttribute("thisWeeklyCounts", service.getCountsForThisWeek());
		m.addAttribute("lastWeeklyCounts", service.getCountsForLastWeek());

		return "top";
	}

}
