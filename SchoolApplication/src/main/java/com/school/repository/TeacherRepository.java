package com.school.repository;

import java.util.List;


import org.springframework.http.ResponseEntity;

import com.school.entity.Teacher;
import com.school.exception.DatabaseException;
import com.school.exception.TeacherNotFoundException;

public interface TeacherRepository {
	boolean checkTeacher(Long id) throws TeacherNotFoundException;
	ResponseEntity<String> addTeacherDetails(Teacher teacherDeteails);
	ResponseEntity<?> getAllTeacherDetails();
	ResponseEntity<String> updateTeacherDetails(Long id,Teacher teacherDetails) throws DatabaseException;
	ResponseEntity<String> deleteTeacherDetails(Long id) throws DatabaseException;
	ResponseEntity<?> getParticularTeacherDetails(Long id) throws DatabaseException;
}
