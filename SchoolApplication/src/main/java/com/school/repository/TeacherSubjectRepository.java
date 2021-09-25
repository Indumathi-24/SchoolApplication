package com.school.repository;

import com.school.dto.TeacherSubject;
import com.school.exception.DatabaseException;

public interface TeacherSubjectRepository {
	Long assignTeacherSubject(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws DatabaseException ;
	Long updateTeacherSubjectAssign(Long teacherId,String subjectCode,TeacherSubject teacherSubjectDetails) throws DatabaseException ;
	Long deleteTeacherSubjectAssign(Long teacherId,String subjectCode) throws DatabaseException ;
}
