package com.example.app.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class RecordDB {
	
	private Integer id;
	
	private Integer registerId;
	
	private LocalDate registeredAt;
	private LocalDate updatedAt;
		
	private LocalDateTime startAt;

	private LocalDateTime endAt;
		
	
	private Integer patientPattern;
	private String consultation;
	private String response;
	
	private Integer editror;

	private Integer approvalStatus;
	
	private List<Integer> symptoms;
	private Staff staff;
	private Patient patient;
	
}
