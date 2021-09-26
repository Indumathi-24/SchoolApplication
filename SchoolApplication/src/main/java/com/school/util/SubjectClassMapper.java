package com.school.util;

import com.school.dto.SubjectClass;
import com.school.entity.ClassEntity;
import com.school.entity.SubjectClassEntity;
import com.school.entity.SubjectEntity;

public class SubjectClassMapper {
     public static SubjectClassEntity mapSubjectClass(SubjectClass subjectClass)
     {
    	 ClassEntity classEntity  = new ClassEntity();
    	 SubjectEntity subjectEntity = new SubjectEntity();
    	 SubjectClassEntity subjectClassEntity = new SubjectClassEntity();
    	 classEntity.setRoomNo(subjectClass.getClassDetail().getRoomNo());
    	 subjectEntity.setCode(subjectClass.getSubject().getCode());
    	 subjectClassEntity.setClassEntity(classEntity);
    	 subjectClassEntity.setSubjectEntity(subjectEntity);
    	 return subjectClassEntity;
     }
}
