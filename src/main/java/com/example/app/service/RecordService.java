package com.example.app.service;

import java.util.List;

import com.example.app.domain.Patient;
import com.example.app.domain.RecordDB;
import com.example.app.domain.Staff;
import com.example.app.domain.SymptomsPattern;
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

	//スタッフ情報の取得
	List<Staff> getStaffType();

	//急病者属性情報の取得
	List<Patient> getPatientType();

	//症状情報の取得
	List<SymptomsPattern> getSymptomType();

	//記録の登録、自動採番取得
	Integer addRecord(RecordDB r);

	//記録の登録（症状）
	void addSymptoms(int recordsId, int symptomsId);
	
	//list.html用の記録を取得
	List<RecordDB> getRecordsWithDetails();
}
