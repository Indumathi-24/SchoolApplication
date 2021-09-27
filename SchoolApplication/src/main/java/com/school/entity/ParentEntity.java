package com.school.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
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
@Table(name="Parent")
public class ParentEntity {
    
	@Id
	private Long id;
	@Column(nullable=false)
	private String fatherName;
	@Column(nullable=false)
	private String motherName;
	@Column(length=10,nullable=false,unique=true)
	private Long contactNo;
	
	@OneToMany(targetEntity=StudentEntity.class,cascade=CascadeType.ALL)
	@JoinTable(name="rollNo")
	@JsonIgnore
	private Set<StudentEntity> studentEntity;
	public ParentEntity(Long id, String fatherName, String motherName, Long contactNo) {
		super();
		this.id = id;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.contactNo = contactNo;
	}
}
