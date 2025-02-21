package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Symptoms {
	private Integer id;
	@NotBlank
	private Integer recordsId;
	private Integer symptomsId;
	
	private SymptomsPattern type;
}
