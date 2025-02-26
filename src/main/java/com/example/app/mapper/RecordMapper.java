package com.example.app.mapper;

import java.util.List;

import com.example.app.domain.RecordDB;
import com.example.app.dto.SymptomsCount;

public interface RecordMapper {
	
	List<RecordDB> selectAll();
	
	RecordDB selectById(int id);
	
	void insert(RecordDB r);
	
	void update(RecordDB r);
	
	void delete(RecordDB r);
	
	int countLastAndThisWeek(int num);
	
	List<SymptomsCount>getTop3TodaySymptoms();
	List<SymptomsCount>getTop3YesterdaySymptoms();

	
}
