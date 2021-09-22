package com.school.service;

import org.springframework.http.ResponseEntity;


import com.school.entity.TeacherSubject;
import com.school.exception.ServiceException;


public interface TeacherSubjectService {
	ResponseEntity<String> assignTeacherSubject(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws  ServiceException;
	ResponseEntity<String> updateTeacherSubjectAssign(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws  ServiceException;
	ResponseEntity<String> deleteTeacherSubjectAssign(Long teacherId,String subjectCode) throws ServiceException;
}
