package com.school.util;

import com.school.dto.TeacherLogin;
import com.school.entity.TeacherEntity;
import com.school.entity.TeacherLoginEntity;

public class TeacherLoginMapper {
     public static TeacherLoginEntity mapTeacherLogin(Long id,TeacherLogin teacherLogin)
     {
    	 TeacherEntity teacherEntity = new TeacherEntity();
    	 TeacherLoginEntity teacherLoginEntity = new TeacherLoginEntity();
    	 teacherEntity.setId(id);
    	 teacherLoginEntity.setPassword(teacherLogin.getPassword());
    	 teacherLoginEntity.setUserId(teacherEntity);
    	 return teacherLoginEntity;
     }
}
