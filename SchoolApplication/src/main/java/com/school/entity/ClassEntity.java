package com.school.entity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@Entity
@Table(name="Class")
public class ClassEntity implements Serializable{
    @Id
    private Long roomNo;
    @Column()
    private String standard;
    @Column()
    private String section;
	@OneToMany(mappedBy="classEntity",fetch=FetchType.LAZY)
	@JsonIgnore
    private Set<StudentEntity> studentEntity;
	@OneToMany(mappedBy="classEntity",fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<SubjectEntity> subjectEntity;
	public ClassEntity() {
		
	}
	public ClassEntity(Long roomNo, String standard, String section) {
		super();
		this.roomNo = roomNo;
		this.standard = standard;
		this.section = section;
	}
}
