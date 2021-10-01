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

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name="Student")
public class StudentEntity implements Serializable{
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
    @ManyToOne(targetEntity=ClassEntity.class,fetch=FetchType.LAZY)
    @JoinColumn(name="roomNo",nullable=false)
    @JsonIgnore
    private ClassEntity classEntity;
    @OneToOne(mappedBy="student",cascade=CascadeType.ALL)
    @JsonIgnore
	private StudentLoginEntity studentLogin;
    @OneToMany(mappedBy="student",fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true)
    @JsonIgnore
    private Set<ResultEntity> result;
	public StudentEntity(Long rollNo, String name, Date dateOfBirth, String gender, String address, ClassEntity classEntity
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
