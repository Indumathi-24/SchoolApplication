package com.school.service;

import java.util.List;

import com.school.dto.SubjectClass;
import com.school.entity.SubjectClassEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

public interface SubjectClassService {
	
	Long assignSubjectClass(SubjectClass subjectClass) throws NotFoundException, ServiceException;
	List<SubjectClassEntity> viewSubjectClass(Long roomNo) throws NotFoundException, ServiceException;
	Long getSubjectClassAssignId(String code,Long roomNo) throws NotFoundException, ServiceException;
	String getSubjectClassAssignDetails(Long id) throws NotFoundException, ServiceException;
}
