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
}
