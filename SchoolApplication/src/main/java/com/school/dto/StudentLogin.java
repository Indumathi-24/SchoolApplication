package com.school.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentLogin {
	private Long autoId;
	private String password;
}
