package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class Patient {
	@NotBlank
	private Integer id;
	
	private String attribute;
}
