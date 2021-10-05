package com.school.service;

import java.util.List;

import com.school.dto.Teacher;
import com.school.entity.TeacherEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;

public interface TeacherService {
	Long addTeacherDetails(Teacher teacherDetails) throws ServiceException, NotFoundException;
	List<TeacherEntity> getAllTeacherDetails() throws ServiceException;
	TeacherEntity updateTeacherDetails(Long id,Teacher teacherDetails) throws ServiceException, NotFoundException ;
	TeacherEntity deleteTeacherDetails(Long id) throws ServiceException, NotFoundException;
	TeacherEntity getParticularTeacherDetails(Long id) throws ServiceException, NotFoundException;
}
