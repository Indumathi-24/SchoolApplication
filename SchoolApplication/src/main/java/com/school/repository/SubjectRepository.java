package com.school.repository;

import java.util.List;
import com.school.exception.ClassNotFoundException;
import com.school.exception.DatabaseException;
import com.school.exception.SubjectNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.school.entity.Student;
import com.school.entity.Subject;

public interface SubjectRepository {

	ResponseEntity<String> addSubject(Long roomNo,Subject subject) throws DatabaseException;
	ResponseEntity<?> getAllSubject(Long roomNo) throws DatabaseException;
	ResponseEntity<?> getParticularSubject(Long roomNo,String code) throws DatabaseException ;
	ResponseEntity<String> updateSubject(Long roomNo,String code,Subject subject) throws  DatabaseException;
	ResponseEntity<String> deleteSubject(Long roomNo, String code) throws DatabaseException;
}
