package com.school.entity;

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
import lombok.NoArgsConstructor;

//@ToString
@NoArgsConstructor
@Entity
@Table(name="Result")
public class ResultEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long resultId;
	private Long term1;
	private Long term2;
	private Long term3;
	private String term1Status;
	private String term2Status;
	private String term3Status;
	private String result;
	@ManyToOne(targetEntity=StudentEntity.class,fetch=FetchType.LAZY)
	@JoinColumn(name="rollNo",nullable=false)
    @JsonIgnore
	private StudentEntity student;
	
	@ManyToOne(targetEntity=ClassEntity.class,fetch=FetchType.EAGER)
	@JoinColumn(name="roomNo",nullable=false)
	@JsonIgnore
	private ClassEntity classEntity;
	
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
	        if(term1Status.equals("PASS") && term2Status.equals("PASS") && term3Status.equals("PASS"))
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

	public ClassEntity getClassEntity() {
		return classEntity;
	}

	public void setClassEntity(ClassEntity classEntity) {
		this.classEntity = classEntity;
	}

	public String getTerm1Status() {
		return term1Status;
	}

	public void setTerm1Status(String term1Status) {
		this.term1Status = term1Status;
	}

	public String getTerm2Status() {
		return term2Status;
	}

	public void setTerm2Status(String term2Status) {
		this.term2Status = term2Status;
	}

	public String getTerm3Status() {
		return term3Status;
	}

	public void setTerm3Status(String term3Status) {
		this.term3Status = term3Status;
	}
	
	
}
