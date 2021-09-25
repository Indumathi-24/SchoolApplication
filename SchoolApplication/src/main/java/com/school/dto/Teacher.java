package com.school.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teacher {
	private Long id;
	@NotNull
	@Size(max=20)
	private String name;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	@NotNull
	@Size(max=7)
	private String gender;
	@NotNull
	@Size(max=20)
	private String qualification;
	@Email
	@NotNull
	private String email;
	@NotNull
	@Column(length=10)
	private Long contactNo;
	@NotNull
	private String address;
}
