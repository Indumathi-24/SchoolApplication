package com.school.util;

import com.school.dto.TeacherSubject;
import com.school.entity.SubjectEntity;
import com.school.entity.TeacherEntity;
import com.school.entity.TeacherSubjectEntity;

public class TeacherSubjectMapper {
       public static TeacherSubjectEntity mapTeacherSubject(Long id,String subjectCode,TeacherSubject teacherSubject)
       {
    	   TeacherEntity  teacherEntity = new TeacherEntity();
    	   SubjectEntity subjectEntity = new SubjectEntity();
    	   TeacherSubjectEntity teacherSubjectEntity = new TeacherSubjectEntity();
    	   teacherEntity.setId(id);
    	   subjectEntity.setCode(subjectCode);
    	   teacherSubjectEntity.setTeacher(teacherEntity);
    	   teacherSubjectEntity.setSubject(subjectEntity);
    	   return teacherSubjectEntity;
       }
}
