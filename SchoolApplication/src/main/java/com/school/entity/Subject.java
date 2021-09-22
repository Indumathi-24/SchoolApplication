package com.school.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Subject {
  
   @Id
   @Size(max=7)
   private String code;
   @NotNull
   @Size(max=15)
   private String name;
   @ManyToOne(targetEntity=Class.class,fetch=FetchType.LAZY)
   @JoinColumn(name="roomNo",nullable=false)
   @JsonIgnore
   private Class classEntity;
   public Subject(@Size(max = 7) String code, @NotNull @Size(max = 15) String name, Class classEntity) {
		super();
		this.code = code;
		this.name = name;
		this.classEntity = classEntity;
	}
}
