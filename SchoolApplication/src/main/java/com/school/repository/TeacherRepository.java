package com.school.repository;

import java.util.List;

import com.school.dto.Teacher;
import com.school.entity.TeacherEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.TeacherNotFoundException;

public interface TeacherRepository {
	void checkTeacher(Long id) throws TeacherNotFoundException;
	Long addTeacherDetails(Teacher teacherDeteails) throws DatabaseException;
	List<TeacherEntity> getAllTeacherDetails() throws DatabaseException;
	TeacherEntity updateTeacherDetails(Long id,Teacher teacherDetails) throws DatabaseException, NotFoundException;
	TeacherEntity deleteTeacherDetails(Long id) throws DatabaseException, NotFoundException;
	TeacherEntity getParticularTeacherDetails(Long id) throws DatabaseException, NotFoundException;
}
