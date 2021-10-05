package com.school.service;



import java.util.List;

import com.school.dto.Student;
import com.school.entity.StudentEntity;
import com.school.exception.NotFoundException;
import com.school.exception.ServiceException;


public interface StudentService {
     Long addStudent(Long roomNo,Student student) throws ServiceException, NotFoundException;
     List<StudentEntity> getAllStudent(Long roomNo) throws ServiceException, NotFoundException;
     StudentEntity getParticularStudent(Long rollNo) throws ServiceException, NotFoundException;
     StudentEntity updateStudent(Long roomNo,Long rollNo,Student student) throws ServiceException, NotFoundException;
     StudentEntity deleteStudent(Long roomNo,Long rollNo) throws ServiceException, NotFoundException;
     Long getStudentRoomNo(Long roomNo) throws ServiceException, NotFoundException;
     Integer updateStudentPassword(Long rollNo,String password) throws ServiceException, NotFoundException;
}
