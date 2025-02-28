package com.example.app.domain;

import lombok.Data;

@Data
public class Symptoms {
	private Integer id;
	
	private Integer recordsId;
	private Integer symptomsId;
	
	private SymptomsPattern type;
}
