package com.school.repository;
import java.util.List;
import com.school.dto.Student;
import com.school.entity.StudentEntity;
import com.school.exception.DatabaseException;
import com.school.exception.NotFoundException;
import com.school.exception.StudentNotFoundException;


public interface StudentRepository {
    
    void checkStudentRollNo(Long rollNo) throws StudentNotFoundException;
    StudentEntity getStudent(Long rollNo);
	Long addStudent(Long roomNo,Student student) throws DatabaseException;
	List<StudentEntity> getAllStudent(Long roomNo) throws DatabaseException, NotFoundException;
	StudentEntity getParticularStudent(Long rollNo) throws DatabaseException, NotFoundException;
	StudentEntity updateStudent(Long roomNo,Long rollNo,Student student) throws  DatabaseException, NotFoundException;
	StudentEntity deleteStudent(Long roomNo,Long rollNo) throws  DatabaseException, NotFoundException;
	Long getStudentRoomNo(Long roomNo) throws StudentNotFoundException, DatabaseException;
	Integer updateStudentPassword(Long rollNo,String password) throws DatabaseException, NotFoundException;
}
