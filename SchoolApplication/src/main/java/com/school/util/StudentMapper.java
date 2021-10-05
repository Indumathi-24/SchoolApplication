package com.school.util;

import com.school.dto.Student;
import com.school.entity.ClassEntity;
import com.school.entity.StudentEntity;

public class StudentMapper {
     public static StudentEntity mapStudent(Student student,Long roomNo)
     {
    	 StudentEntity studentEntity = new StudentEntity();
    	 ClassEntity classEntity = new ClassEntity();
    	 classEntity.setRoomNo(roomNo);
    	 studentEntity.setRollNo(student.getRollNo());
    	 studentEntity.setName(student.getName());
    	 studentEntity.setGender(student.getGender());
    	 studentEntity.setDateOfBirth(student.getDateOfBirth());
    	 studentEntity.setAddress(student.getAddress());
    	 studentEntity.setPassword(student.getPassword());
    	 studentEntity.setClassEntity(classEntity);
    	 return studentEntity;
     }
}
