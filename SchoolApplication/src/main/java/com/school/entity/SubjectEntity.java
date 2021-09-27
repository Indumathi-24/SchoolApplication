package com.school.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Subject")
public class SubjectEntity {
  
   @Id
   @Size(max=7)
   private String code;
   @NotNull
   @Size(max=15)
   private String name;
   @OneToMany(mappedBy="subjectEntity",fetch=FetchType.EAGER)
   @JsonIgnore
   private Set<SubjectClassEntity> subjectClass;
   public SubjectEntity(@Size(max = 7) String code, @NotNull @Size(max = 15) String name) {
	super();
	this.code = code;
	this.name = name;
   }
   
}
