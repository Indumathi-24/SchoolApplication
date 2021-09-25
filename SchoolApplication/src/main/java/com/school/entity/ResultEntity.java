package com.school.entity;

import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@ToString
@NoArgsConstructor
@Entity
@Table(name="Result")
public class ResultEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long resultId;
	@NotNull
	private Long term1;
	@NotNull
	private Long term2;
	@NotNull
	private Long term3;
	@NotNull
	private String result;
	@ManyToOne(targetEntity=StudentEntity.class,fetch=FetchType.LAZY)
	@JoinColumn(name="rollNo",nullable=false)
	@JsonIgnore
	private StudentEntity student;
	
	public ResultEntity(Long resultId, @NotNull Long term1, @NotNull Long term2, @NotNull Long term3,
			@NotNull String result) {
		super();
		this.resultId = resultId;
		this.term1 = term1;
		this.term2 = term2;
		this.term3 = term3;
		this.result = result;
	}

	public Long getResultId() {
		return resultId;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

	public Long getTerm1() {
		return term1;
	}

	public void setTerm1(Long term1) {
		this.term1 = term1;
	}

	public Long getTerm2() {
		return term2;
	}

	public void setTerm2(Long term2) {
		this.term2 = term2;
	}

	public Long getTerm3() {
		return term3;
	}

	public void setTerm3(Long term3) {
		this.term3 = term3;
	}

	public String getResult() {
		if(term1+term2+term3 >=525)
		{
			result="PASS";
		}
		else
		{
			result="FAIL";
		}
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}
	
	
}
