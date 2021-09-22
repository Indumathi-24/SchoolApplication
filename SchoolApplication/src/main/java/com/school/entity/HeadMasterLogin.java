package com.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="HeadMasterLogin")
public class HeadMasterLogin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long autoId;
	@NotNull
	@Column(nullable=false)
	@Size(min=8)
	private String password;
	@OneToOne(targetEntity=HeadMaster.class)
	@JoinColumn(name="id",nullable=false)
	@JsonIgnore
	private HeadMaster headMaster;
}
