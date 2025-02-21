package com.example.app.mapper;

import java.util.List;

import com.example.app.domain.RecordDB;

public interface RecordDBMapper {
	
	List<RecordDB> selectAll();
	
	RecordDB selectById(int id);
	
	void insert(RecordDB r);
	
	void update(RecordDB r);
	
	void delete(RecordDB r);
}
