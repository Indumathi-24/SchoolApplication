package com.school.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentLogin {
	@NotNull
	private Long autoId;
	@NotNull
	@Size(min=8)
	private String password;
}
