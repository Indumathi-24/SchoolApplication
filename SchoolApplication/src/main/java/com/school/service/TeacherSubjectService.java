package com.school.service;

import java.util.List;

import com.school.dto.TeacherSubject;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;


public interface TeacherSubjectService {
	Long assignTeacherSubject(TeacherSubject teacherSubjectDetails) throws  ServiceException, NotFoundException;
	Integer updateTeacherSubjectAssign(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws  ServiceException, NotFoundException;
	Integer deleteTeacherSubjectAssign(Long teacherId,String subjectCode) throws ServiceException, NotFoundException;
	List<Long> getSubjectClassId(Long teacherId) throws NotFoundException, ServiceException;
}
