package com.school.entity;

import java.io.Serializable;



import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name="Student")
public class Student implements Serializable{
	@Id
	private Long rollNo;
	@Column(nullable=false)
    private String name;
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date dateOfBirth;
    @Column(nullable=false)
    private String gender;
    @Column(nullable=false)
    private String address;
    @ManyToOne(targetEntity=Class.class,fetch=FetchType.LAZY)
    @JoinColumn(name="roomNo",nullable=false)
    @JsonIgnore
    private Class classEntity;
    @OneToOne(mappedBy="student")
    @JsonIgnore
	private StudentLogin studentLogin;
    @OneToMany(mappedBy="student",fetch=FetchType.LAZY)
    @JsonIgnore
    private Set<Result> result;
    //@ManyToOne(targetEntity=Parent.class,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    //@JoinColumn(name="id")
    //@JsonIgnore
    //private Parent parentEntity;
	public Student(Long rollNo, String name, Date dateOfBirth, String gender, String address, Class classEntity
			) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.classEntity = classEntity;
		
	}
    
}
