package com.example.app.service;

import java.util.List;

public interface RecordDBService {
	
	//今日と昨日の相談件数を取得
	Integer[] getCountsForTodayAndYesterday();
	
	
	//今週の相談件数を取得(月～日)
	List<Integer> getCountsForThisWeek();
	
	//先週の相談件数を取得(月～日)
	List<Integer> getCountsForLastWeek();
	
}
