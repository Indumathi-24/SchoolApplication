package com.school.service;

import org.springframework.http.ResponseEntity;

import com.school.entity.Teacher;
import com.school.exception.ServiceException;
import com.school.exception.TeacherNotFoundException;

public interface TeacherService {
	ResponseEntity<String> addTeacherDetails(Teacher teacherDetails);
	ResponseEntity<?> getAllTeacherDetails();
	ResponseEntity<String> updateTeacherDetails(Long id,Teacher teacherDetails) throws ServiceException ;
	ResponseEntity<String> deleteTeacherDetails(Long id) throws ServiceException;
	ResponseEntity<?> getParticularTeacherDetails(Long id) throws ServiceException;
}
