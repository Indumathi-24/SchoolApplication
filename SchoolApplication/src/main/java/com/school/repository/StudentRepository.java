package com.school.repository;
import java.util.List;

import org.springframework.http.ResponseEntity;
import com.school.entity.Student;
import com.school.exception.DatabaseException;
import com.school.exception.StudentNotFoundException;


public interface StudentRepository {
    
    boolean checkStudentRollNo(Long rollNo) throws StudentNotFoundException;
    Student getStudent(Long rollNo);
	Long addStudent(Long roomNo,Student student) throws DatabaseException;
	List<Student> getAllStudent(Long roomNo) throws DatabaseException;
	Student getParticularStudent(Long roomNo,Long rollNo) throws DatabaseException;
	Student updateStudent(Long roomNo,Long rollNo,Student student) throws  DatabaseException;
	Student deleteStudent(Long roomNo,Long rollNo) throws  DatabaseException;
}
