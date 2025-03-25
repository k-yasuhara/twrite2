package com.example.app.mapper;

import org.apache.ibatis.annotations.Param;

public interface SymptomsMapper {

	void insert(@Param("records_id") int records_id, @Param("symptoms_id") int symptoms_id);

}
