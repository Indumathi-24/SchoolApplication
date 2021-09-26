package com.school.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Subject {
	@Size(max=7)
	private String code;
	@NotNull
	@Size(max=15)
	private String name;
}
