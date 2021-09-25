package com.school.service;

import java.util.List;

import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;
import com.school.dto.Subject;
import com.school.entity.SubjectEntity;

public interface SubjectService {
	String addSubject(Long roomNo,Subject subject) throws ServiceException, NotFoundException;
	List<SubjectEntity> getAllSubject(Long roomNo) throws ServiceException, NotFoundException;
	SubjectEntity getParticularSubject(Long roomNo,String code) throws ServiceException, NotFoundException;
	SubjectEntity updateSubject(Long roomNo,String code,Subject subject) throws ServiceException, NotFoundException;
	SubjectEntity deleteSubject(Long roomNo, String code) throws ServiceException, NotFoundException;
}
