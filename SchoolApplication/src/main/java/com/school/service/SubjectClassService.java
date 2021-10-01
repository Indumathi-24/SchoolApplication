package com.school.service;

import java.util.List;

import com.school.dto.SubjectClass;
import com.school.entity.SubjectClassEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

public interface SubjectClassService {
	
	Long assignSubjectClass(SubjectClass subjectClass) throws NotFoundException, ServiceException;
	List<String> viewSubjectClass(Long roomNo) throws NotFoundException, ServiceException;
	Long getSubjectClassAssignId(String code,Long roomNo) throws NotFoundException, ServiceException;
	Long getRoomNoForAssignId(Long id) throws NotFoundException, ServiceException;
	String getSubjectCode(Long roomNo,Long id) throws NotFoundException, ServiceException;
}
