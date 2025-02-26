package com.example.app.service;

import java.util.List;

import com.example.app.dto.SymptomsCount;

public interface RecordService {

	//今日と昨日の相談件数を取得
	Integer[] getCountsForTodayAndYesterday();

	//今日の症状の件数と症状名を取得
	List<SymptomsCount> getTopSymptomsToday();

	//今週の相談件数を取得(月～日)
	List<Integer> getCountsForThisWeek();

	//先週の相談件数を取得(月～日)
	List<Integer> getCountsForLastWeek();

}
