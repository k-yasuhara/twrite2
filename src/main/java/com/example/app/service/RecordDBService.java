package com.example.app.service;

import java.util.List;

public interface RecordDBService {
	
	Integer[] getCountsForTodayAndYesterday();
	
	List<Integer> getCountsForThisAndLasWeek() ;
	
	
}
