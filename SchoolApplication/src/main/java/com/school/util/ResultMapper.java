package com.school.util;

import com.school.dto.Result;
import com.school.entity.ClassEntity;
import com.school.entity.ResultEntity;
import com.school.entity.StudentEntity;

public class ResultMapper {
     public static ResultEntity mapResult(Result result,Long rollNo,Long roomNo)
     {
    	 ResultEntity resultEntity = new ResultEntity();
    	 StudentEntity studentEntity = new StudentEntity();
    	 ClassEntity classEntity = new ClassEntity();
    	 classEntity.setRoomNo(roomNo);
    	 studentEntity.setRollNo(rollNo);
    	 resultEntity.setTerm1(result.getTerm1());
    	 resultEntity.setTerm2(result.getTerm2());
    	 resultEntity.setTerm3(result.getTerm3());
    	 resultEntity.setResult(result.getResult());
    	 resultEntity.setStudent(studentEntity);
    	 resultEntity.setClassEntity(classEntity);
    	 return resultEntity;
     }
}
