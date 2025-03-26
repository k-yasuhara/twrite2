package com.example.app.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RecordDB {

	private Integer id;

	private Integer registerId;

	private LocalDateTime registeredAt;

	private LocalDateTime updatedAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endAt;

	private String consultation;

	private String response;

	private Integer editror;

	private Integer approvalStatus;

	private List<Integer> symptoms;

	private Staff staff;

	private Patient patient;

	private String symptomsName;//list.htmlの症状種別の出力用

}
