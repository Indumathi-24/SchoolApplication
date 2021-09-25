package com.school.util;

import com.school.dto.StudentLogin;
import com.school.entity.StudentEntity;
import com.school.entity.StudentLoginEntity;

public class StudentLoginMapper {
	public static StudentLoginEntity mapStudentLogin(StudentLogin studentLogin,Long rollNo)
    {
  	  StudentLoginEntity studentLoginEntity = new StudentLoginEntity();
  	  StudentEntity studentEntity = new StudentEntity();
  	  studentEntity.setRollNo(rollNo);
  	  studentLoginEntity.setPassword(studentLogin.getPassword());
  	  studentLoginEntity.setStudent(studentEntity);
  	  return studentLoginEntity;
    }
}
