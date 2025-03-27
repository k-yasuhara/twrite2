package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.app.domain.RecordDB;
import com.example.app.dto.SymptomsCount;

public interface RecordMapper {

	List<RecordDB> selectAll();

	RecordDB selectById(int id);

	void insert(RecordDB r);

	void update(RecordDB r);

	void delete(int id);

	int countLastAndThisWeek(int num);

	List<SymptomsCount> getTop3TodaySymptoms();

	List<SymptomsCount> getTop3YesterdaySymptoms();

	//insertした自動採番を取得
	Integer getLastInsertId();

	//一覧画面用
	List<RecordDB> selectAllRecordsWithDetails();

	//一覧画面（未承認）用
	List<RecordDB> findUnapprovedRecords(
			@Param("loginNum") Integer loginNum,
			@Param("approval") Integer approval);

	//閲覧・編集画面用
	RecordDB findById(int id);

	//記録の承認・差し戻し
	void updateApprovalStatus(
			@Param("id") Integer id,
			@Param("approval") Integer approval);
}
