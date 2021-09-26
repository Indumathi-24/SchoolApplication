package com.school.util;

import com.school.dto.TeacherSubject;
import com.school.entity.SubjectClassEntity;
import com.school.entity.TeacherEntity;
import com.school.entity.TeacherSubjectEntity;

public class TeacherSubjectMapper {
       public static TeacherSubjectEntity mapTeacherSubject(TeacherSubject teacherSubject)
       {
    	   TeacherEntity  teacherEntity = new TeacherEntity();
    	   SubjectClassEntity subjectClassEntity = new SubjectClassEntity();
    	   TeacherSubjectEntity teacherSubjectEntity = new TeacherSubjectEntity();
    	   teacherEntity.setId(teacherSubject.getTeacherDetail().getId());
    	   subjectClassEntity.setId(teacherSubject.getSubjectClassDetail().getId());
    	   teacherSubjectEntity.setTeacher(teacherEntity);
    	   teacherSubjectEntity.setSubjectClassEntity(subjectClassEntity);
    	   return teacherSubjectEntity;
       }
}
