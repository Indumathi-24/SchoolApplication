package com.school.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@NoArgsConstructor
@Entity
@Table(name="Class")
public class Class implements Serializable{
    @Id
    private Long roomNo;
    @Column()
    private String standard;
    @Column()
    private String section;
	@OneToMany(mappedBy="classEntity",fetch=FetchType.LAZY)
	@JsonIgnore
    private Set<Student> studentEntity;
	@OneToMany(mappedBy="classEntity",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<Subject> subjectEntity;
	public Class() {
		
	}
	public Class(Long roomNo, String standard, String section) {
		super();
		this.roomNo = roomNo;
		this.standard = standard;
		this.section = section;
	}
}
