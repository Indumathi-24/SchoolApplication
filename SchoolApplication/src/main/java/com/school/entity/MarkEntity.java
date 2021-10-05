package com.school.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name="Mark")
public class MarkEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long markId;
	@Max(100)
	private Long tamil;
	@Max(100)
	private Long english;
	@Max(100)
	private Long maths;
	@Max(100)
	private Long science;
	@Max(100)
	private Long socialScience;
	private String termType;
	private String result;
	@ManyToOne(targetEntity=StudentEntity.class)
	@JoinColumn(name="rollNo",nullable=false)
	private StudentEntity studentEntity;
	public Long getMarkId() {
		return markId;
	}
	public void setMarkId(Long markId) {
		this.markId = markId;
	}
	public Long getTamil() {
		return tamil;
	}
	public void setTamil(Long tamil) {
		this.tamil = tamil;
	}
	public Long getEnglish() {
		return english;
	}
	public void setEnglish(Long english) {
		this.english = english;
	}
	public Long getMaths() {
		return maths;
	}
	public void setMaths(Long maths) {
		this.maths = maths;
	}
	public Long getScience() {
		return science;
	}
	public void setScience(Long science) {
		this.science = science;
	}
	public Long getSocialScience() {
		return socialScience;
	}
	public void setSocialScience(Long socialScience) {
		this.socialScience = socialScience;
	}
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public StudentEntity getStudentEntity() {
		return studentEntity;
	}
	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
	}
}
