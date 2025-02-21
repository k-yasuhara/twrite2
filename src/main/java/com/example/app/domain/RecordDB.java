package com.example.app.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecordDB {
	
	private Integer id;
	
	@NotBlank
	private Integer registerId;
	
	private LocalDate registeredAt;
	private LocalDate updatedAt;
		
	@NotBlank
	private LocalDate startAt;

	@NotBlank
	private LocalDate endAt;
		
	@NotBlank
	private Integer patientPattern;
	private String consultation;
	private String response;
	
	private Integer editror;
	
	@NotBlank
	private Integer staffId;

	private Integer approvalStatus;
	
	private Symptoms symptoms;
	private Staff staff;
	
}
