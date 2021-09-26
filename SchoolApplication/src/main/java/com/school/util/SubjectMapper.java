package com.school.util;

import com.school.dto.Subject;
import com.school.entity.SubjectEntity;

public class SubjectMapper {
      public static SubjectEntity mapSubject(Subject subject)
      {
    	  SubjectEntity subjectEntity = new SubjectEntity();
    	  subjectEntity.setCode(subject.getCode());
    	  subjectEntity.setName(subject.getName());
    	  return subjectEntity;
      }
}