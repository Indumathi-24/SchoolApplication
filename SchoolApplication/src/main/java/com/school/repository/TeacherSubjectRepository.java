package com.school.repository;

import java.util.List;

import com.school.dto.TeacherSubject;
import com.school.exception.DatabaseException;

public interface TeacherSubjectRepository {
	Long assignTeacherSubject(TeacherSubject teacherSubjectDetails) throws DatabaseException ;
	Integer updateTeacherSubjectAssign(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws DatabaseException ;
	Integer deleteTeacherSubjectAssign(Long teacherId,String subjectCode) throws DatabaseException ;
	List<Long> getSubjectClassId(Long teacherId) throws DatabaseException;
}
