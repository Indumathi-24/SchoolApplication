package com.school.util;

import com.school.dto.Teacher;
import com.school.entity.TeacherEntity;

public class TeacherMapper {
     public static TeacherEntity mapTeacher(Teacher teacher)
     {
    	 TeacherEntity teacherEntity = new TeacherEntity();
    	 teacherEntity.setId(teacher.getId());
    	 teacherEntity.setName(teacher.getName());
    	 teacherEntity.setQualification(teacher.getQualification());
    	 teacherEntity.setDateOfBirth(teacher.getDateOfBirth());
    	 teacherEntity.setGender(teacher.getGender());
    	 teacherEntity.setEmail(teacher.getEmail());
    	 teacherEntity.setContactNo(teacher.getContactNo());
    	 teacherEntity.setAddress(teacher.getAddress());
    	 return teacherEntity;
     }
}
