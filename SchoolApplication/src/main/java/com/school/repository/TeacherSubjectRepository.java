package com.school.repository;

import org.springframework.http.ResponseEntity;


import com.school.entity.TeacherSubject;
import com.school.exception.DatabaseException;

public interface TeacherSubjectRepository {
	ResponseEntity<String> assignTeacherSubject(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws DatabaseException ;
	ResponseEntity<String> updateTeacherSubjectAssign(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws DatabaseException ;
	ResponseEntity<String> deleteTeacherSubjectAssign(Long teacherId,String subjectCode) throws DatabaseException ;
}
