package com.school.service;

import com.school.dto.TeacherSubject;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;


public interface TeacherSubjectService {
	Long assignTeacherSubject(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws  ServiceException, NotFoundException;
	Long updateTeacherSubjectAssign(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws  ServiceException, NotFoundException;
	Long deleteTeacherSubjectAssign(Long teacherId,String subjectCode) throws ServiceException, NotFoundException;
}
