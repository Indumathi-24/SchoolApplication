package com.school.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="SubjectAssign")
public class SubjectClassEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(targetEntity=ClassEntity.class,fetch=FetchType.EAGER)
	@JoinColumn(name="roomNo")
	@JsonIgnore
	private ClassEntity classEntity;
	@ManyToOne(targetEntity = SubjectEntity.class,fetch=FetchType.EAGER)
	@JoinColumn(name="code")
	@JsonIgnore
	private SubjectEntity subjectEntity;
	@OneToMany(mappedBy="subjectClassEntity",fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<TeacherSubjectEntity> teacherSubject;
}
