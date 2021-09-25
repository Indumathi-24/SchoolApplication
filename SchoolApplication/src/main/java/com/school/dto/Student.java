package com.school.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
	private Long rollNo;
	private String name;
	private Date dateOfBirth;
	private String gender;
	private String address;
}
