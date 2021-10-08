package com.school.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Result")
public class ResultEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long resultId;
	@Column(columnDefinition = "bigint default -1")
	private Long term1;
	@Column(columnDefinition = "bigint default -1")
	private Long term2;
	@Column(columnDefinition = "bigint default -1")
	private Long term3;
	private String term1Status;
	private String term2Status;
	private String term3Status;
	private String result;
	@ManyToOne(targetEntity=StudentEntity.class)
	@JoinColumn(name="rollNo",nullable=false)
	private StudentEntity student;
	
	@ManyToOne(targetEntity=ClassEntity.class)
	@JoinColumn(name="roomNo",nullable=false)
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

	
	
}
