package com.school.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Parent {
	private Long id;
	private String fatherName;
	private String motherName;
	private Long contactNo;
}
