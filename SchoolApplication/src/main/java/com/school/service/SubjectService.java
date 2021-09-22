package com.school.service;

import org.springframework.http.ResponseEntity;
import com.school.exception.ServiceException;
import com.school.entity.Subject;

public interface SubjectService {
	ResponseEntity<String> addSubject(Long roomNo,Subject subject) throws ServiceException;
	ResponseEntity<?> getAllSubject(Long roomNo) throws ServiceException;
	ResponseEntity<?> getParticularSubject(Long roomNo,String code) throws ServiceException;
	ResponseEntity<String> updateSubject(Long roomNo,String code,Subject subject) throws ServiceException;
	ResponseEntity<String> deleteSubject(Long roomNo, String code) throws ServiceException;
}
