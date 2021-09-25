package com.school.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
	@NotNull
	private Long rollNo;
	@NotNull
    private String name;
	@NotNull
    private Date dateOfBirth;
	@NotNull
    private String gender;
	@NotNull
    private String address;
}
