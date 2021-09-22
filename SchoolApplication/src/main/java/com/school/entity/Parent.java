package com.school.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="Parent")
public class Parent {
    
	@Id
	private Long id;
	@Column(nullable=false)
	private String fatherName;
	@Column(nullable=false)
	private String motherName;
	@Column(length=10,nullable=false,unique=true)
	private Long contactNo;
	//@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="parentEntity")
	//private Set<Student> studentEntity;
	
	@OneToMany(targetEntity=Student.class,cascade=CascadeType.ALL)
	@JoinTable(name="rollNo")
	@JsonIgnore
	private Set<Student> studentEntity;
	public Parent(Long id, String fatherName, String motherName, Long contactNo) {
		super();
		this.id = id;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.contactNo = contactNo;
	}
}
