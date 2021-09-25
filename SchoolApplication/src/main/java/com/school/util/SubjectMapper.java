package com.school.util;

import com.school.dto.Subject;
import com.school.entity.ClassEntity;
import com.school.entity.SubjectEntity;

public class SubjectMapper {
      public static SubjectEntity mapSubject(Subject subject,Long roomNo)
      {
    	  SubjectEntity subjectEntity = new SubjectEntity();
    	  ClassEntity classEntity = new ClassEntity();
    	  classEntity.setRoomNo(roomNo);
    	  subjectEntity.setCode(subject.getCode());
    	  subjectEntity.setName(subject.getName());
    	  subjectEntity.setClassEntity(classEntity);
    	  return subjectEntity;
      }
}