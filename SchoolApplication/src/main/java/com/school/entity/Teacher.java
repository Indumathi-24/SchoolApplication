package com.school.entity;



import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Teacher")
public class Teacher {
	@Id
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
	@OneToOne(mappedBy="userId")
	private TeacherLogin teacherLogin;
	public Teacher(Long id, @NotNull @Size(max = 20) String name, 
			@NotNull Date dateOfBirth, @NotNull @Size(max = 7) String gender,
			@NotNull @Size(max = 20) String qualification, @Email @NotNull String email, @NotNull Long contactNo,
			@NotNull String address) {
		super();
		this.id = id;
		this.name =name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.qualification = qualification;
		this.email = email;
		this.contactNo = contactNo;
		this.address = address;
	}
	
}
