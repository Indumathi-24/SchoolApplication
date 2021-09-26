package com.school.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="TeacherSubject")
public class TeacherSubjectEntity implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long autoId;
	@ManyToOne(targetEntity=TeacherEntity.class,fetch=FetchType.EAGER)
	@JoinColumn(name="teacherId",nullable=false)
	@JsonIgnore
	private TeacherEntity teacher;
	@ManyToOne(targetEntity=SubjectClassEntity.class,fetch=FetchType.EAGER)
	@JoinColumn(name="id",nullable=false)
	@JsonIgnore
	private SubjectClassEntity subjectClassEntity;
}
