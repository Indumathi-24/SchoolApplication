package com.school.dto;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Parent {
	@NotNull
	private Long id;
	@NotNull
	private String fatherName;
	@NotNull
	private String motherName;
	@NotNull
	private Long contactNo;
}
