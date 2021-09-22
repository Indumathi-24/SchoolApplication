package com.school.service;



import java.util.List;

import org.springframework.http.ResponseEntity;
import com.school.entity.Student;
import com.school.exception.ServiceException;


public interface StudentService {
     Long addStudent(Long roomNo,Student student) throws ServiceException;
     List<Student> getAllStudent(Long roomNo) throws ServiceException;
     Student getParticularStudent(Long roomNo,Long rollNo) throws ServiceException;
     Student updateStudent(Long roomNo,Long rollNo,Student student) throws ServiceException;
     Student deleteStudent(Long roomNo,Long rollNo) throws ServiceException;
}
