package com.school.service;

import java.util.List;

import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.dto.Subject;
import com.school.entity.SubjectEntity;

public interface SubjectService {
	String addSubject(Subject subject) throws ServiceException, NotFoundException;
	List<SubjectEntity> getAllSubject() throws ServiceException;
	SubjectEntity getParticularSubject(String code) throws ServiceException, NotFoundException;
	SubjectEntity updateSubject(String code,Subject subject) throws ServiceException, NotFoundException;
	SubjectEntity deleteSubject(String code) throws ServiceException, NotFoundException;
	List<SubjectEntity> getSubjects(List<String> subjectCodeList) throws ServiceException;
}
