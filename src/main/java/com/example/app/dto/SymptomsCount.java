package com.example.app.dto;

import lombok.Data;

@Data
public class SymptomsCount {
	private Integer count;
	private String symptomsName;
	private Integer diffYesterday;
}
