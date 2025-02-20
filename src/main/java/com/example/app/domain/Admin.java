package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Admin {
	
	@NotBlank
	private String loginId;
	
	private String loginPass;
	
	private String name;
	private Integer id;
}
