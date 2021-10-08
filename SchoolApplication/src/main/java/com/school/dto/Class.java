package com.school.dto;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Class {
	@NotNull
	private Long roomNo;
	@NotNull
	private String standard;
	@NotNull
	private String section;
	@NotNull
	@Max(100)
	private Double passPercentage;
}
