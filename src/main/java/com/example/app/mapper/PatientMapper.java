package com.example.app.mapper;

import java.util.List;

import com.example.app.domain.Patient;

public interface PatientMapper {
	List<Patient> selectAll();
}
